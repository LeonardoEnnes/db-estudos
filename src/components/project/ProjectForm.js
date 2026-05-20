import { useState, useEffect } from 'react'; // serve para manipular o estado do formulário, como os valores dos campos e as mensagens de erro

import styles from './styles/ProjectForm.module.css';
import Input from '../form/Input';
import Select from '../form/Select';
import SubmitBtn from '../form/SubmitBtn';

function ProjectForm({btnText}) {

  const [categories, setCategories] = useState([]);
  
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

      <Select 
        text="Selecione a categoria" 
        name="category_id" 
        options={categories}
      />
      <SubmitBtn text={btnText} />
    </form>
  );
}

export default ProjectForm;
