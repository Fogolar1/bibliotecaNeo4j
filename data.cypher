MATCH (n) DETACH DELETE n;

CREATE (Rick:Autores {nome:'Rick Riordan'});
CREATE (Rowling:Autores {nome:'J.K. Rowling'});
CREATE (Machado:Autores {nome:'Machado de Assis'});
CREATE (Clarice:Autores {nome:'Clarice Lispector'});
CREATE (Edgan:Autores {nome:'Edgan Allan Poe'});
CREATE (Orwell:Autores {nome:'George Orwell'});

CREATE (Terror:Categorias {categoria:'Terror'});
CREATE (Acao:Categorias {categoria:'Ação'});
CREATE (Comedia:Categorias {categoria:'Comédida'});
CREATE (Aventura:Categorias {categoria:'Aventura'});
CREATE (Infantil:Categorias {categoria:'Infantil'});
CREATE (Fantasia:Categorias {categoria:'Fantasia'});
CREATE (Romance:Categorias {categoria:'Romance'});
CREATE (Drama:Categorias {categoria:'Drama'});
CREATE (Poesia:Categorias {categoria:'Poesia'});
CREATE (Cronicas:Categorias {categoria:'Crônicas'});
CREATE (Filosofia:Categorias {categoria:'Filosofia'});

CREATE (PercyJackson1:Livros {titulo:'Percy Jackson e o Ladrão de Raios'});
CREATE (HP1:Livros {titulo:'Harry Potter e a Pedra Filosofal'});
CREATE (Casmurro:Livros {titulo:'Dom Casmurro'});
CREATE (Memorias:Livros {titulo:'Memórias Póstumas de Brás Cubas'});
CREATE (Estrela:Livros {titulo:'A hora da estrela'});
CREATE (Corvo:Livros {titulo:'O corvo'});
CREATE (HP5:Livros {titulo:'Harry Potter e a Ordem da Fênix'});

CREATE (SDumont:Enderecos {cidade:'Joinville', bairro:'Aventureiro', logradouro:'Santos Dumont', numero:7199});
CREATE (RWiest:Enderecos {cidade:'Joinville', bairro:'Bom Retiro', logradouro:'Rolf Wiest', numero:333});
CREATE (Atl:Enderecos {cidade:'Balneário Camboriú', bairro:'Centro', logradouro:'Atlântida', numero:237});
CREATE (ExpHolz:Enderecos {cidade:'Joinville', bairro:'América', logradouro:'Expedicionário Holz', numero:38});
CREATE (PHerc:Enderecos {cidade:'Florianópolis', bairro:'Estreito', logradouro:'Ponte Hercílio Luz', numero:0});

CREATE (PNunes:Locadores {nome:'Paulo Nunes', telefone:89974155, email:'pnunes@gmail.com'});
CREATE (AMello:Locadores {nome:'Arthur Mello', telefone:56718455, email:'artm@gmail.com'});
CREATE (LSilva:Locadores {nome:'Lorena Silva', telefone:71765543, email:'lorenaS@gmail.com'});
CREATE (MOliveira:Locadores {nome:'Marcelo Oliveira', telefone:65980010, email:'olivMarcelo@gmail.com'});
CREATE (DSantos:Locadores {nome:'Douglas dos Santos', telefone:76164412, email:'douga10@gmail.com'});
CREATE (DSouza:Locadores {nome:'Diego Souza', telefone:76164412, email:'dsousa@hotmail.com'});

MATCH (PercyJackson1:Livros {titulo:"Percy Jackson e o Ladrão de Raios"}), (Rick:Autores {nome:"Rick Riordan"})
CREATE (PercyJackson1)-[:ESCRITO_POR]->(Rick);
MATCH (HP1:Livros {titulo:'Harry Potter e a Pedra Filosofal'}), (Rowling:Autores {nome:'J.K. Rowling'})
CREATE (HP1)-[:ESCRITO_POR]->(Rowling);
MATCH (Casmurro:Livros {titulo:'Dom Casmurro'}), (Machado:Autores {nome:'Machado de Assis'})
CREATE (Casmurro)-[:ESCRITO_POR]->(Machado);
MATCH (Memorias:Livros {titulo:'Memórias Póstumas de Brás Cubas'}), (Machado:Autores {nome:'Machado de Assis'})
CREATE (Memorias)-[:ESCRITO_POR]->(Machado);
MATCH (Estrela:Livros {titulo:'A hora da estrela'}), (Clarice:Autores {nome:'Clarice Lispector'})
CREATE (Estrela)-[:ESCRITO_POR]->(Clarice);
MATCH (Corvo:Livros {titulo:'O corvo'}), (Edgan:Autores {nome:'Edgan Allan Poe'})
CREATE (Corvo)-[:ESCRITO_POR]->(Edgan);
MATCH (HP5:Livros {titulo:'Harry Potter e a Ordem da Fênix'}), (Rowling:Autores {nome:'J.K. Rowling'})
CREATE (HP5)-[:ESCRITO_POR]->(Rowling);

MATCH (PercyJackson1:Livros {titulo:"Percy Jackson e o Ladrão de Raios"}), (Aventura:Categorias {categoria:"Aventura"})
CREATE (PercyJackson1)-[:PERTENCE_A]->(Aventura);
MATCH (HP1:Livros {titulo:'Harry Potter e a Pedra Filosofal'}), (Fantasia:Categorias {categoria:'Fantasia'})
CREATE (HP1)-[:PERTENCE_A]->(Fantasia);
MATCH (Casmurro:Livros {titulo:'Dom Casmurro'}), (Romance:Categorias {categoria:'Romance'})
CREATE (Casmurro)-[:PERTENCE_A]->(Romance);
MATCH (Memorias:Livros {titulo:'Memórias Póstumas de Brás Cubas'}), (Romance:Categorias {categoria:'Romance'})
CREATE (Memorias)-[:PERTENCE_A]->(Romance);
MATCH (Estrela:Livros {titulo:'A hora da estrela'}), (Romance:Categorias {categoria:'Romance'})
CREATE (Estrela)-[:PERTENCE_A]->(Romance);
MATCH (Corvo:Livros {titulo:'O corvo'}), (Drama:Categorias {categoria:'Drama'})
CREATE (Corvo)-[:PERTENCE_A]->(Drama);
MATCH (HP5:Livros {titulo:'Harry Potter e a Ordem da Fênix'}), (Fantasia:Categorias {categoria:'Fantasia'})
CREATE (HP5)-[:PERTENCE_A]->(Fantasia);

MATCH(PNunes:Locadores {nome:'Paulo Nunes'}), (SDumont:Enderecos {cidade:'Joinville', bairro:'Aventureiro', logradouro:'Santos Dumont', numero:7199})
CREATE (PNunes)-[:MORA_EM]->(SDumont);
MATCH(AMello:Locadores {nome:'Arthur Mello'}), (Atl:Enderecos {cidade:'Balneário Camboriú', bairro:'Centro', logradouro:'Atlântida', numero:237})
CREATE (AMello)-[:MORA_EM]->(Atl);
MATCH(LSilva:Locadores {nome:'Lorena Silva'}), (RWiest:Enderecos {cidade:'Joinville', bairro:'Bom Retiro', logradouro:'Rolf Wiest', numero:333})
CREATE (LSilva)-[:MORA_EM]->(RWiest);
MATCH(MOliveira:Locadores {nome:'Marcelo Oliveira'}), (RWiest:Enderecos {cidade:'Joinville', bairro:'Bom Retiro', logradouro:'Rolf Wiest', numero:333})
CREATE (MOliveira)-[:MORA_EM]->(RWiest);
MATCH(DSantos:Locadores {nome:'Douglas dos Santos'}), (ExpHolz:Enderecos {cidade:'Joinville', bairro:'América', logradouro:'Expedicionário Holz', numero:38})
CREATE (DSantos)-[:MORA_EM]->(ExpHolz);
MATCH(DSouza:Locadores {nome:'Diego Souza'}), (SDumont:Enderecos {cidade:'Joinville', bairro:'Aventureiro', logradouro:'Santos Dumont', numero:7199})
CREATE (DSouza)-[:MORA_EM]->(SDumont);

MATCH (PNunes:Locadores {nome:'Paulo Nunes'}), (PercyJackson1:Livros {titulo:"Percy Jackson e o Ladrão de Raios"})
CREATE (PNunes)-[:LOCOU {dataInicio:date('2023-05-20'), dataFim:date('2023-07-20')}]->(PercyJackson1);
MATCH (AMello:Locadores {nome:'Arthur Mello'}), (HP1:Livros {titulo:'Harry Potter e a Pedra Filosofal'})
CREATE (AMello)-[:LOCOU {dataInicio:date('2023-02-01'), dataFim:date('2023-02-16')}]->(HP1);
MATCH (LSilva:Locadores {nome:'Lorena Silva'}), (Casmurro:Livros {titulo:'Dom Casmurro'})
CREATE (LSilva)-[:LOCOU {dataInicio:date('2023-02-06'), dataFim:date('2023-03-16')}]->(Casmurro);
MATCH (MOliveira:Locadores {nome:'Marcelo Oliveira'}), (Memorias:Livros {titulo:'Memórias Póstumas de Brás Cubas'})
CREATE (MOliveira)-[:LOCOU {dataInicio:date('2023-04-21'), dataFim:date('2023-05-16')}]->(Memorias);