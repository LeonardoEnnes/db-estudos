import styles from './styles/ProjectForm.module.css';
import Input from '../form/Input';
import Select from '../form/Select';
import SubmitBtn from '../form/SubmitBtn';

function ProjectForm({btnText}) {
  return (
    <form className={styles.form}>
      <Input
        type="text"
        text="Nome do Projeto"
        name="name"
        placeholder="Insira o nome do projeto"
        handleOnChange={() => {}}
        value=""
      />

      <Input
        type="number"
        text="Orçamento Total"
        name="budget"
        placeholder="Insira o orçamento total"
        handleOnChange={() => {}}
        value=""
      />

      <Select text="Selecione a categoria" name="category_id"/>
      <SubmitBtn text={btnText} />
    </form>
  );
}

export default ProjectForm;
