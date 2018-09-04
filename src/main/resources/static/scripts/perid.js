document.getElementById('zoeken').onclick = zoekFiliaal;
function zoekFiliaal() {
	const filiaalIdVak = document.getElementById('filiaalId');
	if (!filiaalIdVak.validity.valid) {
		alert("Ongeldige id");
		filiaalIdVak.focus();
		return;
	}
	const request = new XMLHttpRequest();
	request.open("GET", filiaalIdVak.value, true);
	request.setRequestHeader('accept', 'application/json');
	request.onload = responseIsBinnengekomen;
	request.send();
}
function responseIsBinnengekomen() {
	switch (this.status) {
	case 200:
		const filiaalResource = JSON.parse(this.responseText);
		const filiaal = filiaalResource.filiaal;
		document.getElementById('naam').innerHTML = filiaal.naam;
		const adres = filiaal.adres;
		document.getElementById('adres').innerHTML = adres.straat + ' '
				+ adres.huisNr + ' ' + adres.postcode + ' ' + adres.gemeente;
		break;
	case 404:
		alert('Filiaal bestaat niet');
		break;
	default:
		alert("Technisch probleem");
	}
}