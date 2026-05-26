import styles from './styles/Message.module.css';
import { useState, useEffect} from 'react';

function Message ({type, msg}) {


    const [visible, setVisible] = useState(false); // começa como false, ou seja, a mensagem não aparece

    // fazendo o timer para mudar a condi dependendo de uma condicção
    useEffect(() => {
        if (!msg) {
            setVisible(false);
            return;
        }

        setVisible(true);
        
        // esse timer é para a mensagem desaparecer depois de 3 segundos
        const timer = setTimeout(() => {
            setVisible(false);
        }, 3000);

        return () => clearTimeout(timer); // limpa o timer quando o componente é desmontado ou quando msg muda
    }, [msg]) 

    return (
        <>
            {visible && (
                <div className={`${styles.message} ${styles[type]}`}>{msg}</div>
            )}
        </>

    )
}

export default Message; 