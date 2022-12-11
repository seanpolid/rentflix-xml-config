
async function getUsers() {
	const response = await fetch("./api/test");
	console.log(response.text());
};

getUsers();

