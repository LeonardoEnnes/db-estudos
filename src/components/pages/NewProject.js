import styles from '../layout/styles/NewProject.module.css';
import ProjectForm from '../project/ProjectForm';

function NewProject() {
    return (
        <div className={styles.newproject_container}>
            <h1 className={styles.h1}>Criar Projeto</h1>
            <p className={styles.p}>Crie seu projeto para depois adicionar os serviços</p>
            <ProjectForm btnText="Criar Projeto" />
        </div>
    )
}

export default NewProject;