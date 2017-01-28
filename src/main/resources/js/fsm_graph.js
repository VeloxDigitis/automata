function graph(x) {
    image = Viz(x, { format: "png-image-element", engine: "dot" });
    return image;
}