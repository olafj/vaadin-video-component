package com.github.olafj.vaadin.flow;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.shared.Registration;

/***
 *
 * this implementation fetches video-stream as blob in javascript,
 * so seeking is available after load.
 *
 */
@JavaScript("./com_github_olafj_vaadin_flow_BlobSourceVideo.js")
public class BlobSourceVideo extends Video {

    private static final String BLOB_SOURCE = "blob-src";

    @Override
    protected String getVideoSourceAttributeName() {
        return BLOB_SOURCE;
    }

    @Override
    public void setSource(String url) {
        super.setSource(url);
        this.loadBlobSource();
    }

    @Override
    public void setSource(AbstractStreamResource streamResource) {
        super.setSource(streamResource);
        this.loadBlobSource();
    }

    private void loadBlobSource() {

        this.runBeforeClientResponse(ui -> {
            this.getElement().executeJs(
                    "window.com_github_olafj_vaadin_flow_BlobSourceVideo_loadSource(this)");
        });

    }

    private void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

    public Registration addBlobSourceLoadedListener(ComponentEventListener<BlobSourceLoadedEvent> eventListener) {
        return ComponentUtil.addListener(this, BlobSourceLoadedEvent.class, eventListener);
    }

    @DomEvent("blob-src-loaded")
    public static class BlobSourceLoadedEvent extends ComponentEvent<BlobSourceVideo> {

        public BlobSourceLoadedEvent(BlobSourceVideo source, boolean fromClient) {
            super(source, fromClient);
        }
    }
}
