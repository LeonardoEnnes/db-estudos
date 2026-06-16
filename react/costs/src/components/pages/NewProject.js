import styles from '../layout/styles/NewProject.module.css';
import ProjectForm from '../project/ProjectForm';
import { useNavigate } from 'react-router-dom';

function NewProject() {

    const navigate = useNavigate();

    function createPost(project) {
        // initialize cost and services
        project.cost = 0;
        project.services = [];

        fetch('http://localhost:5000/projetos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(project), // mandando os dados no body
        })
            .then((resp) => resp.json())
            .then((data) => {
                console.log(data);
                navigate('/projects'); // 
            })
            .catch((err) => console.log(err));
    }

    return (
        <div className={styles.newproject_container}>
            <h1 className={styles.h1}>Criar Projeto</h1>
            <p className={styles.p}>Crie seu projeto para depois adicionar os serviços</p>
            <ProjectForm handleSubmit={createPost} btnText="Criar Projeto" />
        </div>
    )
}

export default NewProject;