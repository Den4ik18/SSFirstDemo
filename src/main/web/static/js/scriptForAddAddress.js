function addAddress() {
    let div = $('<div/>').appendTo($('#inputTable'));
    let tr0 = $('<div/>').html('Address').appendTo(div);
    let div1 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label1 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Street').appendTo(div1);
    let div2 = $('<div/>', {class: 'col-sm-4'}).appendTo(div1);
    let input1 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'street',
        required: true
    }).appendTo(div2);

    let div3 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label2 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('City').appendTo(div3);
    let div4 = $('<div/>', {class: 'col-sm-4'}).appendTo(div3);
    let input2 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'city',
        required: true
    }).appendTo(div4);

    let div5 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label3 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Zip Code').appendTo(div5);
    let div6 = $('<div/>', {class: 'col-sm-4'}).appendTo(div5);
    let input3 = $('<input/>', {
        class: 'form-control',
        type: 'number',
        name: 'zipCode',
        required: true
    }).appendTo(div6);
    let div7 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let div8 = $('<div/>', {class: 'col-sm-4'}).appendTo(div7);
    let btn = $('<input/>', {
        class: 'form-control',
        type: 'button',
        name: 'btn',
        id: 'btn',
        value: 'Delete'
    }).appendTo(div8);
    btn.click(function () {
        div.remove();
    });
}