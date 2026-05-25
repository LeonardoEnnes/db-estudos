import styles from './styles/Loading.module.css';
import { FaSpinner } from 'react-icons/fa';

function Loading () {
    return (
        <div className={styles.loading}>
            <FaSpinner className={styles.loader} />
        </div>
    );
}

export default Loading;