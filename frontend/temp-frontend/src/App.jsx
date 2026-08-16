import { useState } from "react";
import "./App.css";

function App() {
  const [resume, setResume] = useState({
    name: "",
    email: "",
    skills: "",
    education: "",
    experience: ""
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setResume({
      ...resume,
      [e.target.name]: e.target.value
    });
  };

  const submitResume = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/resumes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          ...resume,
          experience: Number(resume.experience)
        })
      });

      if (response.ok) {
        setMessage("Resume submitted successfully!");
      } else {
        setMessage("Failed to submit resume.");
      }
    } catch (error) {
      setMessage("Backend is not running.");
    }
  };

  return (
    <div className="app">
      <h1>AI Resume Screening</h1>

      <form onSubmit={submitResume}>
        <input
          name="name"
          placeholder="Candidate Name"
          value={resume.name}
          onChange={handleChange}
        />

        <input
          name="email"
          placeholder="Email"
          value={resume.email}
          onChange={handleChange}
        />

        <input
          name="skills"
          placeholder="Skills (Java, Spring Boot, SQL)"
          value={resume.skills}
          onChange={handleChange}
        />

        <input
          name="education"
          placeholder="Education"
          value={resume.education}
          onChange={handleChange}
        />

        <input
          name="experience"
          type="number"
          placeholder="Experience"
          value={resume.experience}
          onChange={handleChange}
        />

        <button type="submit">
          Submit Resume
        </button>
      </form>

      {message && <p>{message}</p>}
    </div>
  );
}

export default App;