# Video

Vaadin 14 Java integration of HTML Video-Tag

<code>
  
Video video = new Video();
  
video.setWidth("540px");
  
video.setControls(true);
  
video.setSource(Paths.get("./target/test-classes/sample.mp4"));
  
video.setPosterSource(Paths.get("./target/test-classes/cover.jpeg"));
  

aContainer.add(video);
  
</code>

Additional class BlobSourceVideo fetches video fully as blob, so seeking is available

