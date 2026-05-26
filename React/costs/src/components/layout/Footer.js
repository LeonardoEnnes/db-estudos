import { FaFacebook, FaInstagram, FaLinkedin } from "react-icons/fa";

import Styles from "./styles/Footer.module.css";

function Footer(params) {
    return (
        <footer className={Styles.footer}>
            <div>
                <ul className={Styles.social_list}>
                    <li><a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
                        <FaFacebook />
                    </a></li>
                    <li><a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
                        <FaInstagram />
                    </a></li>
                    <li><a href="https://linkedin.com" target="_blank" rel="noopener noreferrer">
                        <FaLinkedin />
                    </a></li>
                </ul>
                <p className={Styles.copy_right}>
                    <span>&copy; 2023 My Company. All rights reserved.</span>
                </p>
            </div>
        </footer>
    );
}

export default Footer;