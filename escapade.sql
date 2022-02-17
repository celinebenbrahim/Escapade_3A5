-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 fév. 2022 à 19:38
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `escapade`
--

-- --------------------------------------------------------

--
-- Structure de la table `agencedelocation`
--

CREATE TABLE `agencedelocation` (
  `id` int(11) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `numTel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `billet`
--

CREATE TABLE `billet` (
  `id` int(11) NOT NULL,
  `dateAller` date NOT NULL,
  `dateRetour` date NOT NULL,
  `type` int(11) NOT NULL,
  `prix` double NOT NULL,
  `compagnieAerienne` varchar(70) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `Type` enum('simple','double','Triple','quadruple','suite') NOT NULL,
  `vueSurMer` enum('yes','no') NOT NULL,
  `description` varchar(255) NOT NULL,
  `prixNuitée` double NOT NULL,
  `status` enum('disponible','indisponible') NOT NULL,
  `idHotel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

CREATE TABLE `destination` (
  `id` int(11) NOT NULL,
  `pays` varchar(20) NOT NULL,
  `ville` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `prixTotal` double NOT NULL,
  `date` date NOT NULL,
  `idClient` int(11) NOT NULL,
  `prixFinal` int(11) NOT NULL,
  `idPromotion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `guide`
--

CREATE TABLE `guide` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `pernom` varchar(20) NOT NULL,
  `nationnalite` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `langue` varchar(10) NOT NULL,
  `idSiteTouristique` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `matriculeFiscale` varchar(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL,
  `nbrEtoile` int(11) NOT NULL,
  `description` varchar(80) NOT NULL,
  `nbChambreTotal` int(11) NOT NULL,
  `idDestination` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `idClient` int(11) NOT NULL,
  `idMoyenTransport` int(11) NOT NULL,
  `datePrise` date NOT NULL,
  `dateRetour` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `moyendetransport`
--

CREATE TABLE `moyendetransport` (
  `id` int(11) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `capacite` int(11) NOT NULL,
  `description` varchar(30) NOT NULL,
  `status` enum('disponible','indisponible') NOT NULL,
  `tarifJournée` double NOT NULL,
  `idAgence` int(11) NOT NULL,
  `idDestination` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `taux` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `idClient` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_chambre`
--

CREATE TABLE `reservation_chambre` (
  `idClient` int(11) NOT NULL,
  `idChambre` int(11) NOT NULL,
  `dateArrivee` date NOT NULL,
  `dateAller` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_sitetouristique`
--

CREATE TABLE `reservation_sitetouristique` (
  `idClient` int(11) NOT NULL,
  `idSiteTouristique` int(11) NOT NULL,
  `idGuide` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `sitetourstique`
--

CREATE TABLE `sitetourstique` (
  `id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `description` varchar(255) NOT NULL,
  `prix` double NOT NULL,
  `idDestination` int(11) NOT NULL,
  `idGuide` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `numTel` int(11) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `login` varchar(30) NOT NULL,
  `mdp` varchar(30) NOT NULL,
  `role` enum('admin','client') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agencedelocation`
--
ALTER TABLE `agencedelocation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `billet`
--
ALTER TABLE `billet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_BilletClient` (`idClient`);

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Fk_HotelChambre` (`idHotel`);

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Fk_ClientFacture` (`idClient`),
  ADD KEY `Fk_PromoFacture` (`idPromotion`);

--
-- Index pour la table `guide`
--
ALTER TABLE `guide`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SiteTouristiqueGuide` (`idSiteTouristique`);

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_HotelDestination` (`idDestination`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`idClient`,`idMoyenTransport`),
  ADD KEY `FK_MoyenTransportLocation` (`idMoyenTransport`);

--
-- Index pour la table `moyendetransport`
--
ALTER TABLE `moyendetransport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Fk_AgenceMoyen` (`idAgence`),
  ADD KEY `Fk_MoyenTransportDestination` (`idDestination`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Fk_UtilisateurReclamation` (`idClient`);

--
-- Index pour la table `reservation_chambre`
--
ALTER TABLE `reservation_chambre`
  ADD PRIMARY KEY (`idClient`,`idChambre`),
  ADD KEY `Fk_chambreReservationChambre` (`idChambre`);

--
-- Index pour la table `reservation_sitetouristique`
--
ALTER TABLE `reservation_sitetouristique`
  ADD PRIMARY KEY (`idClient`,`idSiteTouristique`),
  ADD KEY `FK_SiteTouristiqueRservation` (`idSiteTouristique`),
  ADD KEY `idGuide` (`idGuide`);

--
-- Index pour la table `sitetourstique`
--
ALTER TABLE `sitetourstique`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SiteTouristiqueDestination` (`idDestination`),
  ADD KEY `Fk_GuideSiteTouristique` (`idGuide`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agencedelocation`
--
ALTER TABLE `agencedelocation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `billet`
--
ALTER TABLE `billet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `guide`
--
ALTER TABLE `guide`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `moyendetransport`
--
ALTER TABLE `moyendetransport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sitetourstique`
--
ALTER TABLE `sitetourstique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `billet`
--
ALTER TABLE `billet`
  ADD CONSTRAINT `FK_BilletClient` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD CONSTRAINT `Fk_HotelChambre` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `Fk_ClientFacture` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `Fk_PromoFacture` FOREIGN KEY (`idPromotion`) REFERENCES `promotion` (`id`);

--
-- Contraintes pour la table `guide`
--
ALTER TABLE `guide`
  ADD CONSTRAINT `FK_SiteTouristiqueGuide` FOREIGN KEY (`idSiteTouristique`) REFERENCES `sitetourstique` (`id`);

--
-- Contraintes pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `FK_HotelDestination` FOREIGN KEY (`idDestination`) REFERENCES `destination` (`Id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `FK_ClientLocation` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_MoyenTransportLocation` FOREIGN KEY (`idMoyenTransport`) REFERENCES `moyendetransport` (`id`);

--
-- Contraintes pour la table `moyendetransport`
--
ALTER TABLE `moyendetransport`
  ADD CONSTRAINT `Fk_AgenceMoyen` FOREIGN KEY (`idAgence`) REFERENCES `agencedelocation` (`Id`) ON DELETE CASCADE,
  ADD CONSTRAINT `Fk_MoyenTransportDestination` FOREIGN KEY (`idDestination`) REFERENCES `destination` (`Id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `Fk_UtilisateurReclamation` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `reservation_chambre`
--
ALTER TABLE `reservation_chambre`
  ADD CONSTRAINT `Fk_ClientReservationChambre` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `Fk_chambreReservationChambre` FOREIGN KEY (`idChambre`) REFERENCES `chambre` (`id`);

--
-- Contraintes pour la table `reservation_sitetouristique`
--
ALTER TABLE `reservation_sitetouristique`
  ADD CONSTRAINT `FK_ClientRservation` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_SiteTouristiqueRservation` FOREIGN KEY (`idSiteTouristique`) REFERENCES `sitetourstique` (`id`),
  ADD CONSTRAINT `idGuide` FOREIGN KEY (`idGuide`) REFERENCES `guide` (`id`);

--
-- Contraintes pour la table `sitetourstique`
--
ALTER TABLE `sitetourstique`
  ADD CONSTRAINT `FK_SiteTouristiqueDestination` FOREIGN KEY (`idDestination`) REFERENCES `destination` (`Id`),
  ADD CONSTRAINT `Fk_GuideSiteTouristique` FOREIGN KEY (`idGuide`) REFERENCES `guide` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
