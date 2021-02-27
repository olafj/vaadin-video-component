package com.github.olafj.vaadin.flow;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.server.AbstractStreamResource;

/***
 *
 * this implementation fetches video-stream as blob in javascript,
 * so seeking is available after load.
 *
 */
public class BlobSourceVideo extends Video {

    private static final String BLOB_SOURCE = "blob-src";

    @Override
    protected String getVideoSourceAttributeName() {
        return BLOB_SOURCE;
    }

    @Override
    public void setSource(String url) {
        super.setSource(url);

        if(url != null && !"".equals(url)) {
            this.loadBlobSource();
        } else {
            this.getElement().removeAttribute(BLOB_SOURCE);
        }

    }

    @Override
    public void setSource(AbstractStreamResource streamResource) {
        super.setSource(streamResource);
        
        if(streamResource != null) {
            this.loadBlobSource();
        } else {
            this.getElement().removeAttribute(BLOB_SOURCE);
        }
    }

    private void loadBlobSource() {

        this.runBeforeClientResponse(ui -> {
            this.getElement().executeJs(
                    "fetch(this.getAttribute('blob-src'), { method: 'GET' })" +
                                ".then(response => response.blob())" +
                                ".then(value => this.src = URL.createObjectURL(value))" +
                                ".finally(this.removeAttribute('blob-src'));");
        });

    }

    private void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }
}
