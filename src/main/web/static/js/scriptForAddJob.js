function addWork() {
    var div = $('<div/>').appendTo($('#inputTable'));
    var div1 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    var label1 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Company name').appendTo(div1);
    var div2 = $('<div/>', {class: 'col-sm-4'}).appendTo(div1);
    var input1 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'companyName',
        required: true
    }).appendTo(div2);

    var div3 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    var label2 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Start date').appendTo(div3);
    var div4 = $('<div/>', {class: 'col-sm-4'}).appendTo(div3);
    var input2 = $('<input/>', {
        class: 'form-control',
        type: 'date',
        name: 'startDate',
        required: true
    }).appendTo(div4);

    var div5 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    var label3 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('End date').appendTo(div5);
    var div6 = $('<div/>', {class: 'col-sm-4'}).appendTo(div5);
    var input3 = $('<input/>', {
        class: 'form-control',
        type: 'date',
        name: 'endDate',
        required: true
    }).appendTo(div6);

    var div7 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    var label4 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Job position').appendTo(div7);
    var div8 = $('<div/>', {class: 'col-sm-4'}).appendTo(div7);
    var input4 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'position',
        required: true
    }).appendTo(div8);

    var div9 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    var div10 = $('<div/>', {class: 'col-sm-4'}).appendTo(div9);
    var btn = $('<input/>', {
        class: 'form-control',
        type: 'button',
        name: 'btn',
        id: 'btn',
        value: 'Delete'
    }).appendTo(div10);
    btn.click(function () {
        div.remove();
    });
}