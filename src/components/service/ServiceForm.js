import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import Input from "../form/Input";
import SubmitButton from "../form/SubmitBtn";
import style from "../pages/Project.module.css";

function ServiceForm({ handleSubmit, btnText, projectData }) {
  const [service, setService] = useState({});

  function submit(e) {
    e.preventDefault();
    //projectData.services.push(service) // vai pegar o service e adicionar dentro do array de serviços do projeto
    if (!service.name || !service.cost) {
      alert("Por favor, preencha o nome e o custo do serviço!");
      return; 
  }
    handleSubmit(service);
  }

  function handleChange(e) {
    setService({ ...service, [e.target.name]: e.target.value }); // vai pegar o valor do input e colocar dentro do service, usando o name do input para identificar qual campo do service deve ser atualizado
  }

  return (
    <form onSubmit={submit} className={style.form}>
      <Input
        type="text"
        text="Nome do serviço"
        name="name"
        placeholder="Insira o nome do serviço"
        handleOnChange={handleChange}
      />

      <Input
        type="number"
        text="Custo do serviço"
        name="cost"
        placeholder="Insira o custo do serviço"
        handleOnChange={handleChange}
      />

      <Input
        type="text"
        text="Descrição"
        name="description"
        placeholder="Insira a descrição do serviço"
        handleOnChange={handleChange}
      />
      <SubmitButton text={btnText} />
    </form>
  );
}

export default ServiceForm;
