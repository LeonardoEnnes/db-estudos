import styles from '../layout/styles/Home.module.css';
import LinkButton from '../layout/LinkButton';

// ter uma img p/ import
//import savings from '../../img/savings.svg';

function Home(params) {
    return (
        <section className={styles.home_container}>
            <h1>Bem vindo <span>ao nosso site</span></h1>
            <p>Comece a gerenciar seus projetos</p>
            <LinkButton to="/newProject" text="Criar Projeto" />
            {/* <a href={savings} alt="Saiba mais">Saiba mais</a> */}
        </section>
    )
}

export default Home;