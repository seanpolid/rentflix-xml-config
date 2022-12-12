"use strict";

const userNav = {
	
    headerNav : document.querySelector("header nav"),
	userImg : document.querySelector(".customer"),
    optionsContainer : document.createElement("div"),
	visible : false, 

	init : function() {
        if (this.userImg) {
            this.createOptionsContainer();
            this.userImg.addEventListener("click", this.toggleContainerView, false);
        }
	},
	
	createOptionsContainer : function() {
		this.optionsContainer.classList.add("userNav");
		
		let options;
		if (this.userImg.classList.contains("admin")) {
			options = this.createAdminOptions();
		} else {
            options = this.createCustomerOptions();
        }
        this.optionsContainer.appendChild(options);
        this.headerNav.appendChild(this.optionsContainer);
	},

    createAdminOptions : function() {
        let adminOptionsContainer = document.createElement("ul");
        const options = ["Add a movie", "Logout"];
        for (const option of options) {
            let listItem = document.createElement("li");
            let anchor = document.createElement("a");
            anchor.textContent = option;

            let link;
            if (option === "Add a movie") {
                link = "./movie";
            } else {
                link = `./${option.toLowerCase()}`;
            }
            anchor.setAttribute("href", link);
            listItem.appendChild(anchor);
            adminOptionsContainer.appendChild(listItem);
        }
        return adminOptionsContainer;
    },

    createCustomerOptions : function() {
        let customerOptions = document.createElement("ul");
        const options = ["Library", "Order History", "Logout"];
        for (const option of options) {
            let listItem = document.createElement("li");
            let anchor = document.createElement("a");
            anchor.textContent = option;

            let link;
            if (option ==="Order History") {
                link = "./orders";
            } else {
                link = `./${option.toLowerCase()}`;
            }
            anchor.setAttribute("href", link);
            listItem.appendChild(anchor);
            customerOptions.appendChild(listItem);
        }
        return customerOptions;
    },

    toggleContainerView : function() {
        let classList = userNav.optionsContainer.classList;
        console.log(classList.contains("visible"));
        if (classList.contains("visible")) {
            classList.remove("visible");
        } else {
            classList.add("visible");
        }
    }
	
}

userNav.init();