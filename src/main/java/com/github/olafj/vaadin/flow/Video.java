package com.github.olafj.vaadin.flow;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag("video")
public class Video extends HtmlContainer {

    private static String ATTR_CROSS_ORIGIN = "crossorigin";
    private static String ATTR_CONTROLS = "controls";
    private static String ATTR_SOURCE = "src";

    public Video() {
        super();
    }

    public void setSource(String url) {
        this.getElement().setAttribute(ATTR_SOURCE, url);
    }

    public void setSource(AbstractStreamResource streamResource) {
        this.getElement().setAttribute(ATTR_SOURCE, streamResource);
    }

    public void setSource(Path path) {
        this.setSource(new StreamResource(path.getFileName().toString(), () -> {
            try {
                return Files.newInputStream(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public String getSource() {
        return this.getElement().getAttribute(ATTR_SOURCE);
    }

    public void setCrossOrigin(boolean crossOrigin) {
        this.getElement().setAttribute(ATTR_CROSS_ORIGIN, crossOrigin);
    }

    public Boolean getCrossOrigin() {
        return this.getElement().hasAttribute(ATTR_CROSS_ORIGIN);
    }

    public void setControls(boolean controls) {
        this.getElement().setAttribute(ATTR_CONTROLS, controls);
    }

    public Boolean getControls() {
        return this.getElement().hasAttribute(ATTR_CONTROLS);
    }
}
