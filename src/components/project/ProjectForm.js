import { useState, useEffect } from 'react'; // serve para manipular o estado do formulário, como os valores dos campos e as mensagens de erro

import styles from './styles/ProjectForm.module.css';
import Input from '../form/Input';
import Select from '../form/Select';
import SubmitBtn from '../form/SubmitBtn';

function ProjectForm({ handleSubmit, btnText, projectData }) {

  const [categories, setCategories] = useState([]);
  const [project, setProject] = useState(projectData || {});
  
  // explicao: O useEffect é um hook do React que permite executar efeitos colaterais em componentes funcionais. 
  // Ele é usado para realizar operações como buscar dados de uma API, configurar assinaturas ou limpar recursos quando o componente é desmontado. No exemplo fornecido, 
  // o useEffect é utilizado para buscar as categorias de um endpoint específico ('http://localhost:5000/categorias') quando o componente é montado. A função fetch é chamada para fazer 
  // a requisição GET, e os dados retornados são convertidos para JSON. Em seguida, o estado categories é atualizado com os dados recebidos, ou com um array vazio caso a resposta seja nula ou indefinida. O useEffect garante que essa operação seja executada apenas uma vez,
  //  quando o componente é renderizado pela primeira vez, evitando chamadas desnecessárias à API em renderizações subsequentes. 
  useEffect(() => {
    fetch('http://localhost:5000/categorias', { 
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        setCategories(data || []); 
      })
      .catch((err) => console.log(err));
  }, []);

  const submit = (e) => {
    e.preventDefault(); 
    handleSubmit(project);
  }

  function handleChange(e) {
    setProject({ ...project, [e.target.name]: e.target.value }); // O operador spread (...) é usado para criar uma cópia do objeto project existente, e a sintaxe [e.target.name]: e.target.value é utilizada para atualizar dinamicamente a propriedade do objeto com base no nome do campo de entrada (name) e seu valor correspondente (value). Isso permite que o estado do projeto seja atualizado corretamente à medida que o usuário interage com os campos do formulário.
  }

  function handleSelect(e) {
    setProject({ ...project, 
      category: { id: e.target.value, name: e.target.options[e.target.selectedIndex].text } }); // O operador spread (...) é usado para criar uma cópia do objeto project existente, e a sintaxe category: { id: e.target.value, name: e.target.options[e.target.selectedIndex].text } é utilizada para atualizar a propriedade category do objeto project com um novo objeto que contém o id e o nome da categoria selecionada. O id é obtido a partir do valor do campo de seleção (e.target.value), enquanto o nome é obtido a partir do texto da opção selecionada (e.target.options[e.target.selectedIndex].text). Isso permite que o estado do projeto seja atualizado corretamente quando uma categoria é selecionada no formulário.
  }

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="text"
        text="Nome do Projeto"
        name="name"
        placeholder="Insira o nome do projeto"
        handleOnChange={handleChange}
        value={project.name ? project.name : ''}
      />

      <Input
        type="number"
        text="Orçamento Total"
        name="budget"
        placeholder="Insira o orçamento total"
        handleOnChange={handleChange}
        value={project.budget ? project.budget : ''}
      />

      <Select 
        text="Selecione a categoria" 
        name="category_id" 
        options={categories}
        handleOnChange={handleSelect}
        value={project.category ? project.category.id : ''}
      />
      <SubmitBtn text={btnText} />
    </form>
  );
}

export default ProjectForm;
