// Funksjonen som bestemmer og kjører validering for det aktuelle feltet
function validateFelt(felt) {
    switch (felt.id) {
        case "fornavn":
            validateFornavn(felt);
            break;
        case "etternavn":
            validateEtternavn(felt);
            break;
        case "mobil":
            validateMobil(felt);
            break;
        case "passord":
            let passordValid = validatePassord(felt);
            let passordRepetertValid = validatePassordRepetert(document.getElementById("passordRepetert"), felt); // Validerer repetert passord mot nytt passord
            return passordValid && passordRepetertValid;
            break;
        case "passordRepetert":
            validatePassordRepetert(felt, document.getElementById("passord"));
            break;
        case "kjonn":
            validateKjonn(felt);
            break;
        default:
            return;
    }
}



// Validerer fornavn
function validateFornavn(fornavn) { 
    let valid = true;

    
    if (/^[A-ZÆØÅa-zæøå\- ]{2,19}$/.test(fornavn.value) === false) {
        fornavn.setCustomValidity("Fornavn skal være 2-20 tegn og kan inneholde bokstaver (inkl. æøåÆØÅ), bindestrek og mellomrom. Første tegn skal være en stor bokstav.");
        valid = false;
    } else {
        fornavn.setCustomValidity(""); 
    }
    
    return valid;
}

// Validerer etternavn
function validateEtternavn(etternavn){
    let valid = true;
    
    if(/^[A-ZÆØÅa-zæøå\-]{2,19}$/.test(etternavn.value) === false){
        etternavn.setCustomValidity("Etternavn skal være 2-20 tegn og kan inneholde bokstaver (inkl. æøåÆØÅ) og bindestrek. Første tegn skal være en stor bokstav.");
        valid = false;
    } else {
        etternavn.setCustomValidity("");
    }
    
    
    return valid;
}

// Validerer mobil
function validateMobil(mobil){
   let valid = true;
   
   if(/^[0-9]{8}$/.test(mobil.value) === false){
    mobil.setCustomValidity("Mobilnummer må være eksakt 8 sifre")
    valid = false;
   } else {
    mobil.setCustomValidity("");
   }
   
   return valid;
}


// Validerer passord
function validatePassord(passord){
    let valid = true;
    
    if(/^(?=.*[0-9])(?=.*[\W_])[A-Za-z0-9\W_]{7,}$/.test(passord.value) === false){
        passord.setCustomValidity("Passord må ha en minimumslengde på 7. Man må også ha minst 1 tall og minst 1 spesialtegn.")
        valid = false;
    } else {
        passord.setCustomValidity("");
    }
    
    return valid;
}

// Validerer at repetert passord er identisk med første passord
function validatePassordRepetert(passordRepetert, passord){
    let valid = true;
    
    if(passordRepetert.value !== passord.value){
        passordRepetert.setCustomValidity("Repetert passord må være identisk med det første passordet");
        valid = false;
    } else {
        passordRepetert.setCustomValidity("");
    }
    
    return valid;
}

// Validerer kjønn
function validateKjonn() {
    const kjonn = document.querySelector('input[name="kjonn"]:checked');
    let valid = true;
    
    if (!kjonn) {
        valid = false;
        // Setter en tilpasset feilmelding for radioknappene
        document.querySelectorAll('input[name="kjonn"]').forEach(radio => {
            radio.setCustomValidity("Du må velge kjønn.");
        });
    } else {
        // Fjerner feilmelding hvis valgt
        document.querySelectorAll('input[name="kjonn"]').forEach(radio => {
            radio.setCustomValidity("");
        });
    }
    
    return valid;
}



// Legg til en eventlistener på alle relevante input-felter uten radioknappene for kjønn
const inputs = document.querySelectorAll("#fornavn, #etternavn, #mobil, #passord, #passordRepetert");
inputs.forEach(input => {
    input.addEventListener("input", function() {
        // Kjør validering for det aktuelle feltet
        validateFelt(this);
        this.reportValidity(); // Tvinger nettleseren til å oppdatere gyldighetsstatusen
    });
});

// Legg til eventlistener for radioknapper
const kjonnInputs = document.querySelectorAll('input[name="kjonn"]');
kjonnInputs.forEach(input => {
    input.addEventListener("change", function() {
        validateKjonn(); // Validerer når radioknapp endres
        this.reportValidity(); // Tvinger nettleseren til å oppdatere gyldighetsstatusen
    });
});


// Setter eventlistener til å sjekke gyldighet av brukerinput når skjemaet forsøker å sende inn
document.getElementById("skjema").addEventListener("submit", function(event) {
    

    let valid = true;
    event.preventDefault();

    // Validerer alle input-felter utenom radioknappene for kjønn
    inputs.forEach(input => {
        const gyldigFelt = validateFelt(input);
        if (gyldigFelt === false) {
            valid = false;
            input.reportValidity(); // Viser feilmelding for første ugyldige felt
          
        }
    });
    
    // Sjekker om kjønn er validert
    if (validateKjonn() === false) {
        valid = false;
        this.reportValidity();
    }

    if (valid) {
        this.submit();
    } else {
        console.log("ugyldig, sendes ikke");
    }
});
