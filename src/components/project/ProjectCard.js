import style from './styles/ProjectCard.module.css';
import {BsPencil, BsFillTrashFill } from 'react-icons/bs';
import { Link } from 'react-router-dom';

function ProjectCard({ id, name, budget, category, handleRemove }) {
    return (
        <div className={style.project_card}>
            <h4>{name}</h4>
            <p><span>Orçamento:</span> {budget}</p>
            <p className={style.category_text}>
                <span className={style[category?.name?.toLowerCase().replace(/\s+/g, '_') || 'default']}></span>
                {category ? category.name : 'Sem categoria'}
            </p>
            
            <div className={style.project_card_actions}>
                <Link to={`/edit/${id}`} className={style.project_card_action}>
                    <BsPencil /> Editar
                </Link>
                <button onClick={() => handleRemove(id)}>
                    <BsFillTrashFill /> Excluir
                </button>
            </div>
        </div>
    );
}

export default ProjectCard;