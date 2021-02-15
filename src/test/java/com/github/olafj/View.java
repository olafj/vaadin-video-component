package com.github.olafj;

import com.github.olafj.vaadin.flow.Video;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

    public View() {
        Video video = new Video();
        add(video);
    }
}
