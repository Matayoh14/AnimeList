"use-strict";

const output = document.getElementById("output");

const getEpisodes = async () => {
    const res = await axios.get("/episodelist/");
    output.innerHTML = "";
    res.data.forEach(episode => renderEpisode(episode));
}