-- Initialisation automatique de l'entreprise et des départements

INSERT INTO entreprise (nom, adresse) VALUES 
('TechCorp International', '123 Avenue de la Technologie, Abidjan');

INSERT INTO departement (nom, entreprise_id) VALUES 
('Informatique', 1),
('Ressources Humaines', 1),
('Finance', 1),
('Marketing', 1),
('Commercial', 1);

INSERT INTO employes (nom, prenom, fonction, entreprise_id) VALUES 
('OUATTARA', 'El Hadj Fetigue', 'Directeur des Systèmes d''Information', 1),
('DOSSO', 'Mariam', 'Responsable Finance', 1),
('KONE', 'Ibrahim', 'Développeur Full Stack', 1),
('TRAORE', 'Aminata', 'Responsable RH', 1),
('KOUAME', 'Yao', 'Chef de projet Marketing', 1);

INSERT INTO employe_departement (employe_id, departement_id) VALUES 
(1, 1), (2, 3), (3, 1), (4, 2), (5, 4), (5, 5);