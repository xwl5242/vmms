(function (window, document, $) {
    'use strict';

    $("#loginForm").formValidation({
        locale: 'zh_CN',
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'icon wb-check',
            invalid: 'icon wb-close',
            validating: 'icon wb-refresh'
        },
        fields: {
            loginName: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码必须大于6且小于30个字符'
                    }
                }
            },
            validCode: {
                validators: {
                    notEmpty: {
                        enabled: true,
                        message: '验证码不能为空'
                    }
                }
            }
        }
    });

})(window, document, jQuery);