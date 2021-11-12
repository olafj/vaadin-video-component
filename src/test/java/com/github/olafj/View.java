package com.github.olafj;

import com.github.olafj.vaadin.flow.BlobSourceVideo;
import com.github.olafj.vaadin.flow.Video;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

import java.nio.file.Paths;

@Route("")
public class View extends Div {


    public View() {

        Div wrapper = new Div();
        wrapper.setWidth("500px");
        wrapper.getStyle().set("position", "relative");
        ProgressBar pb = new ProgressBar();
        pb.setIndeterminate(true);
        wrapper.add(pb);
        pb.setVisible(true);
        pb.getStyle().set("position", "absolute").set("z-index", "")
                .set("left", "0").set("right", "0").set("top", "50%")
                .set("--lumo-primary-color", "red");

        Video video2 = new BlobSourceVideo();
        video2.getStyle().set("filter", "opacity(0.5)");
        wrapper.add(video2);

        ComponentUtil.addListener(video2, BlobSourceVideo.BlobSourceLoadedEvent.class, blobSourceLoadedEvent -> {
            pb.setVisible(false);
            Notification.show("Video geladen");
            video2.getStyle().remove("filter");
        });

        video2.setWidth("100%");
        video2.setControls(true);
        video2.setSource(Paths.get("./target/test-classes/sample.mp4"));
        video2.setPosterSource(Paths.get("./target/test-classes/cover.jpeg"));
        video2.getStyle().set("object-fit", "cover");
        add(wrapper);
    }
}
