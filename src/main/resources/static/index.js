"use strict";

const output = document.getElementById("output");
const epout = document.getElementById("epout");


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

    const episodeDetailsButton = document.createElement("button");
    episodeDetailsButton.className = "card-link btn-primary";
    episodeDetailsButton.innerText = "Details";
    episodeDetailsButton.addEventListener("click", function () {
        getEpisodes(title);
    });
    animeFooter.appendChild(episodeDetailsButton);

    const deleteAnimeButton = document.createElement("button");
    deleteAnimeButton.className = "card-link btn-primary";
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

    for (let i = 1; i <= this.episodes.value; i++) {
        var data2 = {
            number: i,
            animeTitle: this.title.value
        }
        axios.post("episodelist/create", data2)
            .catch(err => console.error(err));
        console.log(data2);
    }

    axios.post("/animelist/create", data)
        .then(res => {
            getAnime();
            this.reset();
            this.title.focus();
        }).catch(err => console.error(err));

    console.log(this);
});

const deleteAnime = async (id) => {
    const res = await axios.delete(`/animelist/remove/${id}`);
    getAnime();
};

const getEpisodes = async (title) => {
    const res = await axios.get(`/episodelist/${title}`);
    epout.innerHTML = "";
    res.data.forEach(episode => renderEpisode(episode));
}

const renderEpisode = ({ id, animeTitle, title, number, desc }) => {
    const row = document.createElement("div");
    row.className = "row";

    const form = document.createElement("form");
    form.className = "updateForm";
    row.appendChild(form);

    const epheader = document.createElement("h4");
    epheader.className = "epheader";
    epheader.innerText = `${animeTitle} - Episode ${number}`;
    form.appendChild(epheader);

    const titleLabel = document.createElement("label");
    titleLabel.setAttribute("for", "title");
    titleLabel.className = "form-label";
    titleLabel.innerText = "Episode Title";
    form.appendChild(titleLabel);

    const titleInput = document.createElement("input");
    titleInput.setAttribute("id", "epTitle");
    titleInput.setAttribute("name", "epTitle");
    titleInput.setAttribute("type", "text");
    titleInput.className = "form-control";
    titleInput.value = title;
    form.appendChild(titleInput);

    const descLabel = document.createElement("label");
    descLabel.setAttribute("for", "desc");
    descLabel.className = "form-label";
    descLabel.innerText = "Description";
    form.appendChild(descLabel);

    const descInput = document.createElement("input");
    descInput.setAttribute("id", "epDesc");
    descInput.setAttribute("name", "epDesc");
    descInput.setAttribute("type", "text");
    descInput.className = "form-control";
    descInput.value = desc;
    form.appendChild(descInput);

    const submitButton = document.createElement("button");
    submitButton.setAttribute("type", "submit");
    submitButton.className = "btn btn-primary";
    submitButton.innerText = "Save";
    submitButton.addEventListener("click", function () {
        updateEpisode({ id, animeTitle, titleInput, number, descInput });
    });
    form.appendChild(submitButton);

    epout.appendChild(row);
}

const updateEpisode = async ({ id, animeTitle, titleInput, number, descInput }) => {
    const data = {
        id: id,
        animeTitle: animeTitle,
        title: titleInput.value,
        number: number,
        desc: descInput.value
    }
    const res = await axios.put(`/episodelist/update/${id}`, data)
        .catch(err => console.error(err));

    console.log(data);
}