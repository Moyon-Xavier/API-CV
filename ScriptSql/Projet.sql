Create Table Period(
    perno SERIAL,
    name varchar(255),
    Constraint pk_perno primary key (perno)
);

Create Table skills(
    skino Serial,
    Title varchar(255),
    Constraint pk_skinoTitle primary key (skino)
);


Create Table project(
    prono Serial,
    projectName varchar(255),
    dateproject date,
    perno int,
    imageUrl varchar(600),
    description varchar(1200),
    Constraint pk_Projet primary key (prono),
    Constraint fk_period foreign key (perno)
    	references Period (perno) 
);

Create Table linkInProject(
    prono Serial,
    linno Serial,
    title varchar(255),
    lien varchar(290),
    Constraint pk_linno primary key (prono,linno),
    Constraint fk_linno_project foreign key (prono)
	references project(prono)
);


Create Table ytbLink(
    prono Serial,
    ytbno Serial,
    title varchar(255),
    iframe varchar(290),
    Constraint pk_ytbno primary key (prono,ytbno),
    Constraint fk_ytbno_project foreign key (prono)
	references project(prono)
);
Create Table Requirement(
    skino SERIAL,
    prono SERIAL,
    Constraint pk_ProjectSkill primary key(skino,prono),
     Constraint fk_project foreign key (prono)
        references project (prono),
     Constraint fk_skillRequirement foreign key (skino)
        references skills (skino)

)
