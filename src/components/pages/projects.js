import Message from '../layout/Message';
import {useLocation} from 'react-router-dom'; // será usado para acessar a mensagem passada pelo navigate no NewProject.js
import styles from './Projects.module.css';
import Container from '../layout/Container';
import LinkButton from '../layout/LinkButton';

function Projects(params) {
    // acessando a mensagem passada pelo navigate
    const location = useLocation();
    let message = '';
    
    if (location.state) {
        message = location.state.message;
    }


    return (
        <div className={styles.project_container}>
            <div className={styles.title_container}>
                <h1>Meus Projetos</h1>
                <LinkButton to="/newProject" text="Criar Projeto" />
            </div>
                {message && <Message type="success" msg={message}/>}
                <Container customClass="start">
                    <p>pojestos</p>
                </Container>
        </div>
    );
}

export default Projects;