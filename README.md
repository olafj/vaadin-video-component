# Video

Vaadin 14 Java integration of HTML Video-Tag

<code>
Video video = new Video();<br/>
video.setWidth("540px");<br/>
video.setControls(true);<br/>
video.setSource(Paths.get("./target/test-classes/sample.mp4"));<br/>
video.setPosterSource(Paths.get("./target/test-classes/cover.jpeg"));<br/>

aContainer.add(video);
</code>

Additional class BlobSourceVideo fetches video fully as blob, so seeking is available

