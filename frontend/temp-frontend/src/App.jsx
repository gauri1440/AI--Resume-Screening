
import { useState } from "react";
import "./App.css";

function App() {
  const [requiredSkills, setRequiredSkills] = useState("");
  const [preferredSkills, setPreferredSkills] = useState("");
  const [resumeFile, setResumeFile] = useState(null);
  const [result, setResult] = useState(null);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    const file = e.target.files[0];

    if (!file) {
      setResumeFile(null);
      return;
    }

    if (file.type !== "application/pdf") {
      setMessage("Please upload a PDF resume.");
      setResumeFile(null);
      return;
    }

    setResumeFile(file);
    setMessage("");
    setResult(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setMessage("");
    setResult(null);

    if (!requiredSkills.trim()) {
      setMessage("Please enter required skills.");
      return;
    }

    if (!resumeFile) {
      setMessage("Please upload a PDF resume.");
      return;
    }

    setLoading(true);

    try {
      const formData = new FormData();

      formData.append("resume", resumeFile);
      formData.append("requiredSkills", requiredSkills);
      formData.append("preferredSkills", preferredSkills);

      const response = await fetch(
        "http://localhost:8080/api/screening/upload",
        {
          method: "POST",
          body: formData,
        }
      );

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.error || "Screening failed");
      }

      setResult(data);
    } catch (error) {
      console.error(error);
      setMessage(error.message || "Something went wrong.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app">
      <div className="container">

        <div className="hero">
          <div className="badge">AI POWERED RESUME SCREENING</div>

          <h1>
            Smart Resume
            <span> Screening</span>
          </h1>

          <p>
            Upload a resume and instantly check how well the candidate
            matches your job requirements.
          </p>
        </div>

        <div className="card">
          <h2>Job Requirements</h2>

          <form onSubmit={handleSubmit}>

            <label>Required Skills</label>

            <textarea
              placeholder="Example: Java, SQL, React"
              value={requiredSkills}
              onChange={(e) => setRequiredSkills(e.target.value)}
            />

            <label>Preferred Skills</label>

            <textarea
              placeholder="Example: Spring Boot, Git, Docker"
              value={preferredSkills}
              onChange={(e) => setPreferredSkills(e.target.value)}
            />

            <div className="upload-box">
              <h3>Upload Resume</h3>

              <p>Only PDF files are supported</p>

              <label className="file-button">
                Choose PDF
                <input
                  type="file"
                  accept=".pdf,application/pdf"
                  onChange={handleFileChange}
                />
              </label>

              {resumeFile && (
                <p className="file-name">
                  📄 {resumeFile.name}
                </p>
              )}
            </div>

            <button
              type="submit"
              disabled={loading}
            >
              {loading ? "Screening..." : "Screen Resume"}
            </button>

          </form>
        </div>

        {message && (
          <div className="message">
            {message}
          </div>
        )}

        {result && (
          <div className="result-card">

            <h2>Screening Result</h2>

            <div className="score">
              {result.score}%
            </div>

            <h3>{result.status}</h3>

            <div className="candidate">
              <p>
                <strong>Name:</strong> {result.name}
              </p>

              <p>
                <strong>Email:</strong> {result.email}
              </p>
            </div>

            <div className="skills-section">
              <h3>Matched Skills</h3>

              {result.matchedSkills?.map((skill, index) => (
                <span className="skill matched" key={index}>
                  ✓ {skill}
                </span>
              ))}
            </div>

            <div className="skills-section">
              <h3>Missing Skills</h3>

              {result.missingSkills?.map((skill, index) => (
                <span className="skill missing" key={index}>
                  ✗ {skill}
                </span>
              ))}
            </div>

          </div>
        )}

      </div>
    </div>
  );
}

export default App;

