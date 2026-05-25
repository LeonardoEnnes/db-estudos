import Home from "./components/pages/Home";
import Company from "./components/pages/Company";
import Contact from "./components/pages/Contact";
import NewProject from "./components/pages/NewProject";
import Container from "./components/layout/Container";
import Navbar from "./components/layout/Navbar";
import Footer from "./components/layout/Footer";
import Projects from "./components/pages/Projects";
import Project from "./components/pages/Project";

import {
  BrowserRouter as Router,
  Routes,
  Switch, // switch foi substituido por Routes
  Route,
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Container customClass="min-height">
          <Routes>
            <Route path="/" element={<Home />} />

            <Route path="/company" element={<Company />} />

            <Route path="/contact" element={<Contact />} />

            <Route path="/projects" element={<Projects />} />

            <Route path="/newProject" element={<NewProject />} />

            <Route path="/project/:id" element={<Project />} />

          </Routes>
        </Container>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
