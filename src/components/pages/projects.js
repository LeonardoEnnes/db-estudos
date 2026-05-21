import Message from '../layout/Message';
import {useLocation} from 'react-router-dom'; // será usado para acessar a mensagem passada pelo navigate no NewProject.js
import styles from './Projects.module.css';
import Container from '../layout/Container';
import LinkButton from '../layout/LinkButton';
import ProjectCard from '../project/ProjectCard';
import { useState, useEffect } from 'react';
import Loading from '../layout/Loading';

function Projects(params) {

    const [projects, setProjects] = useState([]); // estado para armazenar os projetos
    const [removeLoading, setRemoveLoading] = useState(false); // estado para controlar o carregamento dos projetos

    // acessando a mensagem passada pelo navigate
    const location = useLocation();
    let message = '';
    
    if (location.state) {
        message = location.state.message;
    }

    useEffect(() => {
        // fetch para buscar os projetos no backend
        setTimeout(() => { // simula um tempo de carregamento para mostrar o loading
            
        fetch('http://localhost:5000/projetos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(resp => resp.json())
        .then(data => {
            setProjects(data);
            setRemoveLoading(true);
        })
        .catch(err => console.log(err));
        }, 300); // tempo de carregamento simulado de 300ms
        
    }, []); // o array vazio garante que o useEffect seja executado apenas uma vez, quando o componente for montado

    return (
        <div className={styles.project_container}>
            <div className={styles.title_container}>
                <h1>Meus Projetos</h1>
                <LinkButton to="/newProject" text="Criar Projeto" />
            </div>
                {message && <Message type="success" msg={message}/>}
                <Container customClass="start">
                    
                    {projects.length > 0 && projects.map((project) => (
                        <ProjectCard 
                            id={project.id}
                            name={project.name}
                            budget={project.budget}
                            category={project.category}
                        />
                    ))}
                    {!removeLoading && <Loading />}
                    {removeLoading && projects.length === 0 && (
                        <p>Não há projetos cadastrados.</p>
                    )}
                </Container>
        </div>
    );
}

export default Projects;