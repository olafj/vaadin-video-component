package com.github.olafj.vaadin.flow;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag("video")
public class Video extends HtmlContainer {

    private static final PropertyDescriptor<String, String> PRELOAD_PROPERTY_DESCRIPTOR = PropertyDescriptors
            .attributeWithDefault(VideoAttributes.Preload, VideoAttributes.PreloadType.Auto);

    public Video() {
        super();
    }

    @Override
    public void setWidth(String width) {
        this.getElement().setAttribute(VideoAttributes.Width, width);
    }

    @Override
    public void setHeight(String height) {
        this.getElement().setAttribute(VideoAttributes.Height, height);
    }

    @Override
    public void setHeightFull() {
        this.setHeight("100%");
    }

    @Override
    public void setWidthFull() {
        this.setWidth("100%");
    }

    public void setSource(String url) {
        this.getElement().setAttribute(VideoAttributes.Source, url);
    }

    public void setSource(AbstractStreamResource streamResource) {
        this.getElement().setAttribute(VideoAttributes.Source, streamResource);
    }

    public void setSource(File file) {
        this.setSource(file.toPath());
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
        return this.getElement().getAttribute(VideoAttributes.Source);
    }

    public void setPosterSource(String url) {
        this.getElement().setAttribute(VideoAttributes.Poster, url);
    }

    public void setPosterSource(AbstractStreamResource streamResource) {
        this.getElement().setAttribute(VideoAttributes.Poster, streamResource);
    }

    public void setPosterSource(File file) {
        this.setPosterSource(file.toPath());
    }

    public void setPosterSource(Path path) {
        this.setPosterSource(new StreamResource(path.getFileName().toString(), () -> {
            try {
                return Files.newInputStream(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public void setCrossOrigin(boolean crossOrigin) {
        this.getElement().setAttribute(VideoAttributes.CrossOrigin, crossOrigin);
    }

    public Boolean getCrossOrigin() {
        return this.getElement().hasAttribute(VideoAttributes.CrossOrigin);
    }

    public void setControls(boolean controls) {
        this.getElement().setAttribute(VideoAttributes.Controls, controls);
    }

    public Boolean getControls() {
        return this.getElement().hasAttribute(VideoAttributes.Controls);
    }

    public void setAutoPlay(boolean autoPlay) {
        this.getElement().setAttribute(VideoAttributes.AutoPlay, autoPlay);
    }

    public boolean getAutoPlay() {
        return this.getElement().hasAttribute(VideoAttributes.AutoPlay);
    }

    public void setLoop(boolean loop) {
        this.getElement().setAttribute(VideoAttributes.Loop, loop);
    }

    public boolean getLoop() {
        return this.getElement().hasAttribute(VideoAttributes.Loop);
    }

    public void setPreload(String preload) {
        this.set(PRELOAD_PROPERTY_DESCRIPTOR, preload);
    }

    public String getPreload() {
        return this.get(PRELOAD_PROPERTY_DESCRIPTOR);
    }

    private interface VideoAttributes {
        String CrossOrigin = "crossorigin";
        String Controls = "controls";
        String Source = "src";
        String AutoPlay = "autoplay";
        String Loop = "loop";
        String Preload = "preload";
        String Poster = "poster";
        String Width = "width";
        String Height = "height";

        interface PreloadType {
            String Auto = "auto";
            String MetaData = "metadata";
            String None = "none";
        }
    }

}
