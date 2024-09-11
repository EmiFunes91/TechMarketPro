// Validaciones de formularios
$(function () {

    // Validación de registro de usuario
    var $userRegister = $("#userRegister");

    $userRegister.validate({
        rules: {
            name: {
                required: true,
                lettersonly: true
            },
            email: {
                required: true,
                space: true,
                email: true
            },
            mobileNumber: {
                required: true,
                space: true,
                numericOnly: true,
                minlength: 10,
                maxlength: 12
            },
            password: {
                required: true,
                space: true
            },
            confirmpassword: {
                required: true,
                space: true,
                equalTo: '#pass'
            },
            address: {
                required: true,
                all: true
            },
            city: {
                required: true,
                space: true
            },
            state: {
                required: true
            },
            pincode: {
                required: true,
                space: true,
                numericOnly: true
            },
            img: {
                required: true
            }
        },
        messages: {
            name: {
                required: 'El nombre es obligatorio',
                lettersonly: 'Nombre inválido'
            },
            email: {
                required: 'El email es obligatorio',
                space: 'No se permiten espacios',
                email: 'Email inválido'
            },
            mobileNumber: {
                required: 'El número de teléfono es obligatorio',
                space: 'No se permiten espacios',
                numericOnly: 'Número de teléfono inválido',
                minlength: 'Mínimo 10 dígitos',
                maxlength: 'Máximo 12 dígitos'
            },
            password: {
                required: 'La contraseña es obligatoria',
                space: 'No se permiten espacios'
            },
            confirmpassword: {
                required: 'La confirmación de la contraseña es obligatoria',
                space: 'No se permiten espacios',
                equalTo: 'Las contraseñas no coinciden'
            },
            address: {
                required: 'La dirección es obligatoria',
                all: 'Dirección inválida'
            },
            city: {
                required: 'La ciudad es obligatoria',
                space: 'No se permiten espacios'
            },
            state: {
                required: 'El estado es obligatorio'
            },
            pincode: {
                required: 'El código postal es obligatorio',
                space: 'No se permiten espacios',
                numericOnly: 'Código postal inválido'
            },
            img: {
                required: 'Se requiere una imagen'
            }
        }
    });

    // Validación de pedidos
    var $orders = $("#orders");

    $orders.validate({
        rules: {
            firstName: {
                required: true,
                lettersonly: true
            },
            lastName: {
                required: true,
                lettersonly: true
            },
            email: {
                required: true,
                space: true,
                email: true
            },
            mobileNo: {
                required: true,
                space: true,
                numericOnly: true,
                minlength: 10,
                maxlength: 12
            },
            address: {
                required: true,
                all: true
            },
            city: {
                required: true,
                space: true
            },
            state: {
                required: true
            },
            pincode: {
                required: true,
                space: true,
                numericOnly: true
            },
            paymentType: {
                required: true
            }
        },
        messages: {
            firstName: {
                required: 'El nombre es obligatorio',
                lettersonly: 'Nombre inválido'
            },
            lastName: {
                required: 'El apellido es obligatorio',
                lettersonly: 'Apellido inválido'
            },
            email: {
                required: 'El email es obligatorio',
                space: 'No se permiten espacios',
                email: 'Email inválido'
            },
            mobileNo: {
                required: 'El número de teléfono es obligatorio',
                space: 'No se permiten espacios',
                numericOnly: 'Número de teléfono inválido',
                minlength: 'Mínimo 10 dígitos',
                maxlength: 'Máximo 12 dígitos'
            },
            address: {
                required: 'La dirección es obligatoria',
                all: 'Dirección inválida'
            },
            city: {
                required: 'La ciudad es obligatoria',
                space: 'No se permiten espacios'
            },
            state: {
                required: 'El estado es obligatorio'
            },
            pincode: {
                required: 'El código postal es obligatorio',
                space: 'No se permiten espacios',
                numericOnly: 'Código postal inválido'
            },
            paymentType: {
                required: 'Selecciona el tipo de pago'
            }
        }
    });

    // Validación de restablecimiento de contraseña
    var $resetPassword = $("#resetPassword");

    $resetPassword.validate({
        rules: {
            password: {
                required: true,
                space: true
            },
            confirmPassword: {
                required: true,
                space: true,
                equalTo: '#pass'
            }
        },
        messages: {
            password: {
                required: 'La contraseña es obligatoria',
                space: 'No se permiten espacios'
            },
            confirmPassword: {
                required: 'La confirmación de la contraseña es obligatoria',
                space: 'No se permiten espacios',
                equalTo: 'Las contraseñas no coinciden'
            }
        }
    });

    // Métodos personalizados de validación
    jQuery.validator.addMethod('lettersonly', function (value, element) {
        return /^[^-\s][a-zA-Z_\s-]+$/.test(value);
    });

    jQuery.validator.addMethod('space', function (value, element) {
        return /^[^-\s]+$/.test(value);
    });

    jQuery.validator.addMethod('all', function (value, element) {
        return /^[^-\s][a-zA-Z0-9_,.\s-]+$/.test(value);
    });

    jQuery.validator.addMethod('numericOnly', function (value, element) {
        return /^[0-9]+$/.test(value);
    });

});

// Funciones de carga y manejo de productos y categorías

function loadProducts() {
    fetch('/admin/products')
        .then(response => response.text())  // Cambiado a text() para manejar la respuesta HTML generada por Thymeleaf
        .then(data => {
            document.getElementById('content').innerHTML = data;  // Inyecta el HTML en el contenido
        })
        .catch(error => console.error('Error al cargar productos:', error));
}

function loadCategories() {
    fetch('/admin/category')
        .then(response => response.text())  // Cambiado a text() para manejar la respuesta HTML generada por Thymeleaf
        .then(data => {
            document.getElementById('content').innerHTML = data;  // Inyecta el HTML en el contenido
        })
        .catch(error => console.error('Error al cargar categorías:', error));
}

function editProduct(id) {
    window.location.href = `/admin/editProduct/${id}`;
}

function deleteProduct(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
        fetch(`/admin/deleteProduct/${id}`, { method: 'GET' })
            .then(response => {
                if (response.ok) {
                    loadProducts();  // Recargar la lista de productos después de eliminar
                } else {
                    alert('Error al eliminar el producto');
                }
            })
            .catch(error => console.error('Error al eliminar producto:', error));
    }
}

function editCategory(id) {
    window.location.href = `/admin/loadEditCategory/${id}`;
}

function deleteCategory(id) {
    if (confirm('¿Estás seguro de que deseas eliminar esta categoría?')) {
        fetch(`/admin/deleteCategory/${id}`, { method: 'GET' })
            .then(response => {
                if (response.ok) {
                    loadCategories();  // Recargar la lista de categorías después de eliminar
                } else {
                    alert('Error al eliminar la categoría');
                }
            })
            .catch(error => console.error('Error al eliminar categoría:', error));
    }
}
