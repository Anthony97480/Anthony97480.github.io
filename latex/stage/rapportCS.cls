\LoadClass[a4paper,11pt]{article} % Classe du document

%--------------------- Packages ------------------------

\RequirePackage[french]{babel} %Langue du document
\RequirePackage[utf8]{inputenc} %Caractères spéciaux
\RequirePackage[section]{placeins}%Pour placement de section
\RequirePackage[T1]{fontenc} %Quelques lettres qui sont pas inclus dans UTF-8
\RequirePackage{mathtools} %Paquet pour des équations et symboles mathématiques
\RequirePackage{siunitx} %Pour écrire avec la notation scientifique (Ex.: \num{2e+9})
\RequirePackage{float} %Pour placement d'images
\RequirePackage{graphicx} %Paquet pour insérer des images
\RequirePackage[justification=centering]{caption} %Pour les légendes centralisées
\RequirePackage{subcaption}
\RequirePackage{wallpaper}
\RequirePackage{nomencl}
%\makenomenclature
\RequirePackage{fancyhdr}
%\pagestyle{fancy}
%\fancyheadoffset{1cm}
\setlength{\headheight}{2cm}
\RequirePackage{url}
\RequirePackage[hidelinks]{hyperref}%Paquet pour insérer légendes dans des sous-figures comme Figure 1a, 1b
\RequirePackage[left=2.5cm,right=2.5cm,top=4cm,bottom=4cm]{geometry} %Configuration de la page
\RequirePackage{tikz}

%-------------------- Informations sur le rapport ----------------------

\newcommand{\mention}[1]{\renewcommand{\mention}{#1}}

\newcommand{\master}[1]{\renewcommand{\master}{#1}}
\newcommand{\filiere}[1]{\renewcommand{\filiere}{#1}}

\newcommand{\titreligneun}[1]{\renewcommand{\titreligneun}{#1}}
\newcommand{\titrelignedeux}[1]{\renewcommand{\titrelignedeux}{#1}}
\newcommand{\numeroordre}[1]{\renewcommand{\numeroordre}{#1}}

\newcommand{\soustitre}[1]{\renewcommand{\soustitre}{#1}}
\newcommand{\departement}[1]{\renewcommand{\departement}{#1}}

\newcommand{\eleve}[1]{\renewcommand{\eleve}{#1}}

\newcommand{\tuteuruniv}[1]{\renewcommand{\tuteuruniv}{#1}}
\newcommand{\tuteurentreprise}[1]{\renewcommand{\tuteurentreprise}{#1}}

\newcommand{\logoentreprise}[1]{\renewcommand{\logoentreprise}{#1}}

\newcommand{\dates}[1]{\renewcommand{\dates}{#1}}

\newcommand{\fairemarges}{
\makenomenclature
\pagestyle{fancy}
\fancyheadoffset{0cm}


\chead{\logoentreprise} %Affichage du logo entreprise au top center de la page
\lhead{\includegraphics[scale=0.22]{logos/insa.png}} %Affichage du logo INSA au top left de la page}
\rhead{\nouppercase{\leftmark}}
\rfoot{
    \begin{tikzpicture}[remember picture,overlay]
        \node[inner sep=0pt] at ([shift={(8.15cm,-12.6cm)}]current page.center) {\includegraphics[scale=1]{logos/foot-logo-pfe.png}};
        \node[text=white, align=center] at ([shift={(8.75cm,-13.2cm)}]current page.center) {\thepage};
       %\node[text=black, align=center] at ([shift={(0cm,-13.2cm)}]current page.center) {\textbf{\soustitre}};
        %\node[text=black, align=center] at ([shift={(-6.85cm,-13.3cm)}]current page.center) {Dpt. \departement};
        %\draw[line width=0.4pt] (-15.5,-1) -- (-13,-1);
    \end{tikzpicture}
}
\cfoot{}
\lfoot{}

}

\newcommand{\fairecouverture}{
\begin{titlepage}
% 1st Couverture du Rapport
\includepdf[
  pages=1-3              % Specify the page number(s) to include
  pagecommand={%
    \begin{tikzpicture}[remember picture, overlay]
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -4.73) { \textbf{\textsf{\titreligneun}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -5.63) { \textbf{\textsf{\titrelignedeux}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -6.53) { \textsf{\soustitre}}; % poition south west (-1.32, -5.63)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -7.43) { \textsf{\numeroordre}}; % poition south west (-1.32, -5.63)
    \end{tikzpicture}%
  },% Code to be executed on each page
]{documents/1st-couverture.pdf} % insérer la page de couverture fournie (pdf) dans les {}
\vspace{6cm}
	\vfill
\end{titlepage}
\newpage
}


\newcommand{\annexeObli}{
\begin{titlepage}
% annexe1 hygiène
\includepdf[
  pages=1-6               % Specify the page number(s) to include
  pagecommand={%
    \begin{tikzpicture}[remember picture, overlay]
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -4.73) { \textbf{\textsf{\titreligneun}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -5.63) { \textbf{\textsf{\titrelignedeux}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -6.53) { \textsf{\soustitre}}; % poition south west (-1.32, -5.63)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -7.43) { \textsf{\numeroordre}}; % poition south west (-1.32, -5.63)
    \end{tikzpicture}%
  },% Code to be executed on each page
]{documents/annexeObli.pdf} % insérer la page de couverture fournie (pdf) dans les {}
\vspace{6cm}
	\vfill
\end{titlepage}
\newpage
}


\newcommand{\AnnexeFacul}{
\begin{titlepage}
\includepdf[
  pages=1-7               % Specify the page number(s) to include
  pagecommand={%
    \begin{tikzpicture}[remember picture, overlay]
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -4.73) { \textbf{\textsf{\titreligneun}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{17}{0}\selectfont\sffamily\bfseries] at (-1.32, -5.63) { \textbf{\textsf{\titrelignedeux}}}; % position south west (-1.32, -4.73)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -6.53) { \textsf{\soustitre}}; % poition south west (-1.32, -5.63)
      \node [anchor=south west, font=\fontsize{12.5}{0}\selectfont\sffamily] at (-1.32, -7.43) { \textsf{\numeroordre}}; % poition south west (-1.32, -5.63)
    \end{tikzpicture}%
  },% Code to be executed on each page
]{documents/annexefacul.pdf} % insérer la page de couverture fournie (pdf) dans les {}
\vspace{6cm}
	\vfill
\end{titlepage}
\newpage
}


\newcommand{\tabledematieres}{
\vspace*{\stretch{1}}
\tableofcontents
\vspace*{\stretch{1}}
\newpage
}

%\insererfigure{image,taille,caption,label}
\newcommand{\insererfigure}[4]{
\begin{figure}[ht]
\centering
\includegraphics[width=#2]{#1}
\caption{#3}
\label{fig:#4}
\end{figure}
}

\renewcommand{\familydefault}{\sfdefault}
\newcommand{\mycomment}[1]{}

% Section sans numéro répertorié dans la TOC et dont le titre apparait dans le header de sa page
\newcommand{\sectionwithoutnumber}[1]{
\rhead{\nouppercase{#1}}
\addcontentsline{toc}{section}{#1}
\section*{#1}
}