package com.github.olafj;

import com.github.olafj.vaadin.flow.Video;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.nio.file.Paths;

@Route("")
public class View extends Div {


    public View() {
        Video video = new Video();
        video.setWidth("540px");
        video.setControls(true);
        video.setSource(Paths.get("./target/test-classes/sample.mp4"));
        video.setPosterSource(Paths.get("./target/test-classes/cover.jpeg"));
        video.getStyle().set("object-fit", "cover");
        add(video);
    }
}
