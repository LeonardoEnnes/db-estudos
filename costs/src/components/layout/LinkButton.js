import styles from "./styles/LinkButton.module.css";
import { Link } from "react-router-dom";

function LinkButton({ to, text }) {
    return (
        <Link to={to} className={styles.btn}>
            {text}
        </Link>
    );
}

export default LinkButton;