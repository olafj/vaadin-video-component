window.com_github_olafj_vaadin_flow_BlobSourceVideo = window.com_github_olafj_vaadin_flow_BlobSourceVideo || {

    loadSource: (videoElement) => {
        fetch(videoElement.getAttribute('blob-src'), { method: 'GET' })
            .then(response => response.blob())
            .then(value => videoElement.src = URL.createObjectURL(value))
            .finally(() => {
                const event = new Event('blob-src-loaded');
                videoElement.dispatchEvent(event);
                videoElement.removeAttribute('blob-src');
            });
    },

}

window.com_github_olafj_vaadin_flow_BlobSourceVideo_loadSource = (videoElement) => {
    window.com_github_olafj_vaadin_flow_BlobSourceVideo.loadSource(videoElement);
}