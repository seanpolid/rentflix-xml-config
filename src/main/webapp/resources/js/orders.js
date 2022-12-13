"use strict";

const orders = {

    table : document.querySelector(".orders table"),
    customerId : document.querySelector(".customer").getAttribute("data-customerId"),

    init : function() {
        this.populateTable();
    },

    populateTable : async function() {
        const response = await fetch(`./api/invoices/${this.customerId}`);
        response.json()
        .then(invoices => {
			let allInvoiceData = [];
            for (let invoice of invoices) {
                const orderNum = invoice["id"];
                const date = invoice["date"];
                const total = invoice["total"];
                let movies = [];
                for (let invoiceMovie of invoice.invoiceMovies) {
					movies.push(invoiceMovie["movie"]["name"]);
				}
				allInvoiceData.push([orderNum, date, total, movies]);
            }
            return allInvoiceData;
        })
        .then(allInvoiceData => {
			for (let invoiceData of allInvoiceData) {
				orders.createTableRow(invoiceData);
			}
		})
    },
    
    createTableRow : function(invoiceData) {
		const orderNum = invoiceData[0];
		const date = invoiceData[1];
		const total = invoiceData[2];
		const movies = invoiceData[3];
		
		let row = document.createElement("tr");
		let orderNumCol = document.createElement("td");
		let itemsCol = document.createElement("td");
		let itemsList = document.createElement("ul");
		let dateCol = document.createElement("td");
		let totalCol = document.createElement("td");
		
		orderNumCol.appendChild(document.createTextNode(orderNum));
		dateCol.appendChild(document.createTextNode(date));
		totalCol.appendChild(document.createTextNode(`${total}`));
		
		if (movies) {
			for (const movie of movies) {
				let listItem = document.createElement("li");
				listItem.appendChild(document.createTextNode(movie));
				itemsList.appendChild(listItem);
			}
			itemsList.classList.add("movieList");
			itemsCol.appendChild(itemsList);
		}
		
		
		row.append(orderNumCol, itemsCol, dateCol, totalCol);
		orders.table.appendChild(row);
	}

}

orders.init();