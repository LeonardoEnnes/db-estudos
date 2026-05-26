import style from "./Project.module.css";

import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import Loading from "../layout/Loading";
import Container from "../layout/Container";
import Message from "../layout/Message";
import ProjectForm from "../project/ProjectForm";
import ServiceForm from "../service/ServiceForm";
import ServiceCard from "../service/ServiceCard";
import { parse, v4 as uuidv4 } from "uuid";

function Project() {
  const { id } = useParams(); /// isso ser para pegar o ida da url

  const [project, setProject] = useState([]);
  const [services, setServices] = useState([]);
  const [showProjectForm, setShowProjectForm] = useState(false);
  const [showServiceForm, setShowServiceForm] = useState(false);
  const [message, setMessage] = useState();
  const [type, setType] = useState();

  useEffect(() => {
    setTimeout(() => {
      fetch(`http://localhost:5000/projetos/${id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((resp) => resp.json())
        .then((data) => {
          setProject(data);
          setServices(data.services);
        })
        .catch((err) => console.log(err));
    }, 200);
  }, [id]);

  function editPost(project) {
    setMessage("");
    if (project.budget < project.costs) {
      setMessage("O orçamento não pode ser menor que o custo do projeto!");
      setType("error");
      return false;
    }

    fetch(`http://localhost:5000/projetos/${project.id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(project),
    })
      .then((resp) => resp.json())
      .then((data) => {
        setProject(data);
        setShowProjectForm(false);
        setMessage("Projeto atualizado!");
        setType("success");
      })
      .catch((err) => console.log(err));
  }

  function removeService(id, cost) {

    const servicesUpdate = project.services.filter(
      (service) => service.id !== id
    
    );

    const projectUpdated = project;

    projectUpdated.services = servicesUpdate;
    projectUpdated.costs = parseFloat(projectUpdated.costs) - parseFloat(cost);

    fetch(`http://localhost:5000/projetos/${projectUpdated.id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(projectUpdated),
    })
      .then((resp) => resp.json())
      .then((data) => {
        setProject(projectUpdated);
        setServices(servicesUpdate);
        setMessage("Serviço removido!");
        setType("success");
      })
      .catch((err) => console.log(err));
  }

  function createService(service) {
    setMessage("");

    // 1. O serviço que veio do form ganha um ID próprio
    service.id = uuidv4();

    // 2. Calcula os custos
    const currentCosts = parseFloat(project.costs || 0);
    const serviceCost = parseFloat(service.cost || 0);
    const newCost = currentCosts + serviceCost;

    // 3. Valida o orçamento
    if (newCost > parseFloat(project.budget)) {
      setMessage("Orçamento ultrapassado, verifique o valor do serviço!");
      setType("error");
      return false;
    }

    // 4. Cria o novo objeto do projeto e atualiza os valores
    const updatedProject = { ...project };
    if (!updatedProject.services) {
        updatedProject.services = [];
    }
    
    // Adiciona o novo serviço validado na lista
    updatedProject.services.push(service);
    updatedProject.costs = newCost;

    // 5. Salva no backend
    fetch(`http://localhost:5000/projetos/${updatedProject.id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedProject),
    })
      .then((resp) => resp.json())
      .then((data) => {
        setProject(data);
        setServices(data.services); // Garante que a tela vai atualizar a lista
        setShowServiceForm(false);
        setMessage("Serviço adicionado!");
        setType("success");
      })
      .catch((err) => console.log(err));
  }

  function toggleServiceForm() {
    setShowServiceForm(!showServiceForm);
  }

  function toggleProjectForm() {
    setShowProjectForm(!showProjectForm);
  }

  return (
    <>
      {project.name ? (
        <div className={style.project_details}>
          <Container customClass="column">
            {message && <Message type={type} msg={message} />}

            <div className={style.details_container}>
              <h1>{project.name}</h1>
              <button className={style.btn} onClick={toggleProjectForm}>
                {showProjectForm ? "Fechar" : "Editar projeto"}
              </button>
              {/* serve para exibir os detalhes do projeto */}
              {!showProjectForm ? (
                <div className={style.project_info}>
                  <p>
                    <span>Categoria: </span>
                    {project.category ? project.category.name : "Sem categoria"}
                  </p>
                  <p>
                    <span>Total de orçamento: </span>
                    {project.budget}
                  </p>
                  <p>
                    <span>Total utilizado: </span>
                    {project.costs}
                  </p>
                </div>
              ) : (
                <div className={style.project_info}>
                  <ProjectForm
                    handleSubmit={editPost}
                    btnText="Editar projeto"
                    projectData={project}
                  />
                </div>
              )}
            </div>

            <div className={style.service_form_container}>
              <h2>Adicione um Serviço</h2>
              <button className={style.btn} onClick={toggleServiceForm}>
                {!showServiceForm ? "Adicionar Serviço" : "Fechar"}
              </button>
              <div className={style.project_info}>
                {showServiceForm && (
                  <ServiceForm
                    handleSubmit={createService}
                    btnText="Adicionar Serviço"
                    projectData={project}
                  />
                )}
              </div>

              <h2>Serviços</h2>
              <Container customClass="start">
                {services.length > 0 &&
                  services.map(
                    (service) =>
                      service.name && (
                        <ServiceCard
                          id={service.id}
                          name={service.name}
                          cost={service.cost}
                          description={service.description}
                          key={service.id}
                          handleRemove={removeService}
                        />
                      ),
                  )}
                {services.length === 0 && <p>Não há serviços cadastrados.</p>}
              </Container>
            </div>
          </Container>
        </div>
      ) : (
        <Loading />
      )}
    </>
  );
}

export default Project;
