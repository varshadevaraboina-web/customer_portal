// REGISTRATION FORM
const registerForm = document.getElementById("registerForm");

if (registerForm) {
    registerForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;
        const message = document.getElementById("message");

        message.textContent = "Creating your account...";
        message.style.color = "blue";

        try {
            const response = await fetch(
                "http://localhost:9999/auth/register",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        name: name,
                        email: email,
                        password: password
                    })
                }
            );

            const data = await response.json();

            if (response.ok) {
                message.textContent =
                    "Registration successful! You can now log in.";
                message.style.color = "green";

                registerForm.reset();
            } else {
                message.textContent =
                    data.message || "Registration failed.";
                message.style.color = "red";
            }

        } catch (error) {
            message.textContent =
                "Cannot connect to the server. Check Spring Boot.";
            message.style.color = "red";

            console.error("Registration error:", error);
        }
    });
}


// LOGIN FORM
const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;
        const message = document.getElementById("message");

        message.textContent = "Checking login details...";
        message.style.color = "blue";

        try {
            const response = await fetch(
                "http://localhost:9999/auth/login",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        email: email,
                        password: password
                    })
                }
            );

            const data = await response.json();

            if (response.ok) {
                message.textContent = "Login successful!";
                message.style.color = "green";

                // Dashboard navigation will be added later.
            } else {
                message.textContent =
                    data.message || "Invalid email or password.";
                message.style.color = "red";
            }

        } catch (error) {
            message.textContent =
                "Cannot connect to the server. Check Spring Boot.";
            message.style.color = "red";

            console.error("Login error:", error);
        }
    });
}