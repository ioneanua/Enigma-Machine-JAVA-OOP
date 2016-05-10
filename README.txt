Tema 2 POO -  Enigma Machine

Ioneanu Andrei 324 CA 24.11.2015

Pentru a implementa si rezolva aceasta problema , m am gandit in felul urmator:
O masina Enigma este folosita sa cripteze anumite mesaje; 
Dupa ce am citit documentatia am ajuns la urmatoarea concluzie:
O masina Enigma are nevoie de : un alfabet pe care sa-l foloseasca atunci cand face criptarea,
un plugboard , un reflector , 3 rotoare si un mesaj pe care sa-l cripteze ... asa ca am facut urmatoarele
clase: 
- clasa Plugboard - contine drumul dus si drumul intors; l-am transformat intr-un string si am gandit astfel:
		    	(pozitiile de la 0 la 25 ) daca indexul literei care trebuie sa treaca prin plugboard
			este par , atunci vom inlocui litera cu litera din dreapta ; daca este impar , cu 
			litera din stanga.
-clasa Reflector -  contine cele doua tipuri de reflectoare , B si C ; le-am gandit ca niste matrici pentru a 
			realiza mai usor "intrarea si iesirea literei" din acestea .
-clasa Rotor - un rotor este format din : un notchletter sau doua ; offset rortor si  inel si tipul de rotor , pe care le primim de 
		la intrare ;
-calsa Enigma - aceasta clasa face toate operatiile: mai intai face operatiile cu notch-urile roatoarelor , descrise si in
		javadoc ; ia litera cu litera si o trece prin procesul de criptare: drum dus: iese din plugboard o anumita litera
		ii luam codul acesteia si il scadem la codul offset-ului rotorului respectiv si adunam codul offset-ului inelului ; iese o 
		litera careia ii facem corespondenta cu rotorul corespunzator si o inlocim cu litera din rotor (facem mapare) ; 
		a iesit o litera , o trecem prin reflectorul corespunzator  si ne intoarcem prin rotoare invers de cum am venit ,
		facand urmatorul calcul: index litera care iese (din rotor) = index_litera care a intart(dupa ce a iesit din reflector) + 
		offset rotor folosit - offset inel corespunzator (de trei ori- adica prin cele trei roatoare) dupa care iese o litera
		pe care o trecem prin plugboard si afisam litera "criptata".
-clasa Main - am folosit-o pentru a deschide fisierele de intrare si pentru a initializa campurile respective linilor din fisier
		pentru a realiza operatiile necesare criptarii .
