"use strict";

const output = document.getElementById("output");


const getAnime = async () => {
    const res = await axios.get("/animelist/");
    output.innerHTML = "";
    res.data.forEach(anime => renderAnime(anime));
}

const renderAnime = ({ id, title, genre, episodes, season }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const titleText = document.createElement("p");
    titleText.className = "card-text";
    titleText.innerText = `Title: ${title}`;
    cardBody.appendChild(titleText);

    const genreText = document.createElement("p");
    genreText.className = "card-text";
    genreText.innerText = `Genre: ${genre}`;
    cardBody.appendChild(genreText);

    const episodesText = document.createElement("p");
    episodesText.className = "card-text";
    episodesText.innerText = `Episodes: ${episodes}`;
    cardBody.appendChild(episodesText);

    const seasonText = document.createElement("p");
    seasonText.className = "card-text";
    seasonText.innerText = `Season: ${season}`;
    cardBody.appendChild(seasonText);

    const animeFooter = document.createElement("div");
    animeFooter.className = "card-footer";
    cardBody.appendChild(animeFooter);

    const deleteAnimeButton = document.createElement("a");
    deleteAnimeButton.className = "card-link";
    deleteAnimeButton.innerText = "Delete";
    deleteAnimeButton.addEventListener("click", function () {
        deleteAnime(id);
    });
    animeFooter.appendChild(deleteAnimeButton);

    output.appendChild(column);
}

getAnime();

document.getElementById("createForm").addEventListener('submit', function (event) {
    event.preventDefault();

    console.log("this: ", this);
    console.log("this.title: ", this.title);
    console.log("this.genre: ", this.genre);
    console.log("this.episodes: ", this.episodes);
    console.log("this.season: ", this.season);

    const data = {
        title: this.title.value,
        genre: this.genre.value,
        episodes: this.episodes.value,
        season: this.season.value
    };

    axios.post("/animelist/create", data)
        .then(res => {
            getAnime();
            this.reset();
            this.title.focus();
        }).catch(err => console.error(err));

    console.log(this);
});

const deleteAnime = async (id) => {
    const res = await axious.delete(`/animelist/remove/${id}`);
    getAnime();
};