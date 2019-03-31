function addWork() {
    let div = $('<div/>').appendTo($('#inputTable'));
    let tr0 = $('<div/>').html('Working place').appendTo(div);
    let div1 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label1 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Company name').appendTo(div1);
    let div2 = $('<div/>', {class: 'col-sm-4'}).appendTo(div1);
    let input1 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'companyName',
        required: true
    }).appendTo(div2);

    let div3 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label2 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Start date').appendTo(div3);
    let div4 = $('<div/>', {class: 'col-sm-4'}).appendTo(div3);
    let input2 = $('<input/>', {
        class: 'form-control',
        type: 'date',
        name: 'startDate',
        required: true
    }).appendTo(div4);

    let div5 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label3 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('End date').appendTo(div5);
    let div6 = $('<div/>', {class: 'col-sm-4'}).appendTo(div5);
    let input3 = $('<input/>', {
        class: 'form-control',
        type: 'date',
        name: 'endDate',
        required: true
    }).appendTo(div6);

    let div7 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let label4 = $('<label/>', {class: 'col-sm-2 col-form-label'}).html('Job position').appendTo(div7);
    let div8 = $('<div/>', {class: 'col-sm-4'}).appendTo(div7);
    let input4 = $('<input/>', {
        class: 'form-control',
        type: 'text',
        name: 'position',
        required: true
    }).appendTo(div8);

    let div9 = $('<div/>', {class: 'form-group row'}).appendTo($(div));
    let div10 = $('<div/>', {class: 'col-sm-4'}).appendTo(div9);
    let btn = $('<input/>', {
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