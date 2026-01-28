function Send(event) {
    event.preventDefault() 

    const name = document.getElementById("name").value.trim()
    const email = document.getElementById("email").value.trim()
    const date = document.getElementById("date").value.trim()
    const service = document.getElementById("service").value.trim()
   

    if (!name) {
        Swal.fire({
            title: "Error",
            text: "El nombre es obligatorio",
            icon: "warning"
        });
        return
    }

    if (!email) {
        Swal.fire({
            title: "Error",
            text: "El correo electrónico es obligatorio",
            icon: "warning"
        });
        return
    }

    if (!service) {
        Swal.fire({
            title: "Error",
            text: "Debe seleccionar un servicio",
            icon: "warning"
        });
        return
    }

    if (!date) {
        Swal.fire({
            title: "Error",
            text: "La fecha y hora son obligatorias",
            icon: "warning"
        });
        return
    }

    Swal.fire({
        title: "Muy Bien!",
        text: "Solicitud enviada, te contactaremos pronto",
        icon: "success"
    }).then(() => {
        event.target.submit()
    })
}

const frm = document.getElementById("frm-contact")
frm.addEventListener("submit", Send)
