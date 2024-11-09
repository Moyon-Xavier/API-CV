Create Table Period(
    perno SERIAL,
    name varchar(255),
    Constraint pk_perno primary key (perno)
);

Create Table skills(
    skino Serial,
    Title varchar(255),
    Constraint pk_skinoTitle primary key (skino,Title)
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

Create Table Requirement(
    skino SERIAL,
    prono int,
    Constraint pk_ProjectSkill primary key(skino,prono),
     Constraint fk_project foreign key (prono)
        references project (prono),
     Constraint fk_skillRequirement foreign key (skino)
        references skills (skino)

)
