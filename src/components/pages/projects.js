import Message from '../layout/Message';
import {useLocation} from 'react-router-dom'; // será usado para acessar a mensagem passada pelo navigate no NewProject.js

function Projects(params) {
    // acessando a mensagem passada pelo navigate
    const location = useLocation();
    let message = '';
    
    if (location.state) {
        message = location.state.message;
    }


    return (
        <div>
            <h1>Meus projetos</h1>
            {message && <Message type="success" msg={message}/>}
        </div>
    );
}

export default Projects;