import { useEffect, useState } from "react";
import "./App.css";

function App() {
    const [movies, setMovies] = useState([]);
    const [title, setTitle] = useState("");
    const [genre, setGenre] = useState("ACTION");

    const genres = [
        "ACTION", "COMEDY", "DRAMA", "HORROR",
        "SCI_FI", "ROMANCE", "THRILLER", "FANTASY",
        "ANIMATION", "DOCUMENTARY"
    ];

    // Backend’den filmleri çek
    const fetchMovies = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/movies");
            const data = await res.json();
            setMovies(data);
        } catch (err) {
            console.error("Error fetching movies:", err);
        }
    };

    useEffect(() => {
        fetchMovies();
    }, []);

    // Yeni film ekle
    const addMovie = async () => {
        if (!title) return alert("Film başlığını girin!");
        try {
            const res = await fetch("http://localhost:8080/api/movies", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ title, genre })
            });
            if (res.ok) {
                setTitle("");
                setGenre("ACTION");
                fetchMovies();
            }
        } catch (err) {
            console.error("Error adding movie:", err);
        }
    };

    return (
        <div className="App">
            <h1>Movie App</h1>

            <div className="add-movie">
                <input
                    type="text"
                    placeholder="Film Başlığı"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <select value={genre} onChange={(e) => setGenre(e.target.value)}>
                    {genres.map((g) => (
                        <option key={g} value={g}>{g}</option>
                    ))}
                </select>
                <button onClick={addMovie}>Film Ekle</button>
            </div>

            <h2>Filmler</h2>
            <ul>
                {movies.map((movie) => (
                    <li key={movie.id}>
                        {movie.title} - <strong>{movie.genre}</strong>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;