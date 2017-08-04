(function(angular, $, moment) {
    var _toString = Object.prototype.toString,

        CLASS_NAME = 'annebonny',
        INNER_CLASS_NAME = CLASS_NAME + '-inner',
        CONTAINER_CLASS_NAME = CLASS_NAME + '-container',

        changeMomentKey = {
            quarter: ['year'],
            month: ['year'],
            isoWeek: ['year'],
            week: ['year'],
            dayOfYear: ['year'],
            isoWeekday: ['year', 'isoWeek'],
            weekday: ['year', 'week'],
            day: ['year', 'week'],
            date: ['year', 'month'],
            hour: ['year', 'month', 'date'],
            minute: ['year', 'month', 'date', 'hour'],
            second: ['year', 'month', 'date', 'hour', 'minute'],
            millisecond: ['year', 'month', 'date', 'hour', 'minute', 'second']
        };

    function DatePicker() {
    }

    DatePicker.defaultOptions = {
        type: 'date',
        pickerType: 'panel',
        events: {}
    };

    DatePicker.prototype = {
        // 获取默认日期
        defaultDate: function() { return new Date(); },

        /**
         * 设置日期
         */
        setValue: function(val) {
            this._mdate = this._getMomentDate(val) || this._getMomentDate(this.defaultDate);
            return this;
        },

        getValue: function() {
            return this._mdate;
        },

        /**
         * 触发事件
         */
        trigger: function(name) {
            var cal = this.options.events[name],
                params = Array.prototype.slice.call(arguments, 1);

            params.unshift(this);

            if (cal) {
                cal.apply(this, params);
            }
        },

        /**
         * 修改当前日期
         */
        _changeValue: function(key, change) {
            if (moment.isMoment(change)) {
                var front = changeMomentKey[key];

                if (front) {
                    for (var i = 0; i < front.length; i++) {
                        this._mdate.set(front[i], change.get(front[i]));
                    }

                    this._mdate.set(key, change.get(key));
                }
            }
            else {
                this._mdate.set(key, change);
            }

            this.trigger('change', key, change);

            if (moment.normalizeUnits(key) === 'date') {
                this.close();
            }
        },

        /**
         * 刷新选择面板
         */
        reset: function(options) {
            options = $.extend({}, DatePicker.defaultOptions, options);

            // 创建选择框
            if (!this._picker) {
                this._createPicker();
            }

            // 刷新选择框
            this._resetPicker(options);

            // 创建一个新的容器
            var container = this._createContainer(options).appendTo(this._pickerInner);
            this._container && this._container.remove();
            this._container = container;

            this.options = options;

            return this;
        },

        /**
         * 打开日期选择框
         */
        open: function(options) {
            if (!this._picker) {
                this._createPicker();
            }

            this._picker.show();
        },

        /**
         * 关闭选择面板
         */
        close: function() {
            this._picker.hide();
        },

        /**
         * 创建选择框
         */
        _createPicker: function(options) {
            var self = this, picker, pickerInner;

            picker = $('<div class="' + CLASS_NAME + '" />');
            pickerInner = $('<div />').addClass(INNER_CLASS_NAME).appendTo(picker);

            picker.hide();
            picker.appendTo($('body'));

            picker.on('change.annebonny', function(event, key, number) {
                self._changeValue(key, number);
            });

            this._picker = picker;
            this._pickerInner = pickerInner;
        },

        /**
         * 重置选择框
         */
        _resetPicker: function(newOptions) {
            if (this.options) {
                this._picker.removeClass(CLASS_NAME + '-' + this.options.pickerType);
            }

            this._picker.addClass(CLASS_NAME + '-' + newOptions.pickerType);
        },

        /**
         * 创建容器
         */
        _createContainer: function(options) {
            var type = options.type;

            switch (type) {
            case 'date' :
                return this._createCalendarContainer();
            default:
                return $('<div class="' + CONTAINER_CLASS_NAME + '-unsupported' + '">不支持的日期类型</div>');
            }
        },

        /**
         * 创建日历容器
         */
        _createCalendarContainer: function() {
            var self = this,

                calendar = $('<div class="' + CONTAINER_CLASS_NAME + '-calendar' + '"></div>'),
                hd = this._createCalendarHeader(),
                bd = this._createCalendarPage(this._mdate);

            calendar.append(hd).append(bd);

            this._picker.on('change.annebonny', function(event, key, number) {
                key = moment.normalizeUnits(key);
                if (key === 'year' || key == 'month') {
                    bd.remove();
                    bd = self._createCalendarPage(self._mdate);
                    calendar.append(bd);
                }
            });

            return calendar;
        },

        /**
         * 创建日历容器头部
         */
        _createCalendarHeader: function() {
            var hd = $('<div class="hd"></div>');

            hd.append($('<div class="col year-col" />').append(this._createYearSelect()))
                .append($('<div class="col month-col" />').append(this._createMonthSelect()));

            return hd;
        },

        /**
         * 创建年份选择器
         */
        _createYearSelect: function() {
            var self = this,

                nowYear = this._mdate.year(),
                beginYear = nowYear - 20,
                endYear = beginYear + 40,

                selectHtml = '<select class="year">';

            for (; beginYear <= endYear; beginYear++) {
                selectHtml += '<option value="' + beginYear + '" ' + (beginYear === nowYear ? 'selected' : '') + '>' + beginYear + '</option>';
            }

            selectHtml += '</select>';

            return $(selectHtml).on('change.annebonny', function() {
                var val = parseInt($(this).val(), 10);
                self._picker.trigger('change.annebonny', ['year', val]);
            });
        },

        /**
         * 创建月份选择器
         */
        _createMonthSelect: function() {
            var self = this,

                nowMonth = this._mdate.month(),
                selectHtml = '<select class="month">';

            for (var i = 0;  i < 12; i++) {
                selectHtml += '<option value="' + i + '" ' + (i === nowMonth ? 'selected' : '') + '>' + (i + 1) + '</option>';
            }

            selectHtml += '</select>';

            return $(selectHtml).on('change.annebonny', function() {
                var val = parseInt($(this).val(), 10);
                self._picker.trigger('change.annebonny', ['month', val]);
            });
        },

        /**
         * 获取所传入日期所在月份的月历中每天的 moment 对象
         */
        _getCalendarMdates: function(mdate) {
        },

        /**
         * 创建一张日历
         */
        _createCalendarPage: function(mdate) {
            mdate = moment(mdate).startOf('day');

            var self = this,

                calendar = new Calendar(mdate),
                now = moment().startOf('day'),
                currentMdate = moment(this._mdate).startOf('day'),

                page = $('<div class="' + CLASS_NAME + '-calendar-page"></div>'),
                pageHd = $('<div class="hd"></div>').appendTo(page),
                pageBd = $('<div class="bd"></div>').appendTo(page),

                daysHtml = '',
                daysCount = 0,

                i, l;

            pageHd.append(this._createWeekBar());

            for (i = 0, l = calendar.mdates.length; i < l; i++) {
                var date = calendar.mdates[i];

                if (i % 7 === 0) {
                    daysHtml += '<div class="line">';
                }

                var className = '';

                if (date.diff(now) === 0) { className += 'now '; }

                if (date.diff(currentMdate) === 0) {
                    className += 'selected ';
                }

                if (date.month() < this._mdate.month()) { className += 'prev-month '; }
                if (date.month() > this._mdate.month()) { className += 'next-month '; }

                daysHtml += '<span data-date="' + date.format('YYYY-MM-DD') + '" class="col day ' + className + '">' + date.date() + '</span>';

                if (i % 7 === 6) {
                    daysHtml += '</div>';
                }
            }

            pageBd.append(daysHtml);

            // 绑定点击事件
            pageBd.on('click', '.day', function() {
                var dayCol = $(this),
                    mdate = moment(dayCol.data('date'));

                self._picker.triggerHandler( 'change.annebonny', ['date', mdate]);
                dayCol.closest('.bd').find('.day.selected').removeClass('selected');
                dayCol.addClass('selected');
            });

            return page;
        },

        /**
         * 创建星期条
         */
        _createWeekBar: function() {
            var weekBar = '<div class="' + CLASS_NAME + '-week-bar">',
                weekName = moment._locale._weekdaysMin;

            for (var i = 0; i < weekName.length; i++) {
                weekBar += '<span class="col">' + weekName[i] + '</span>';
            }

            weekBar += '</div>';

            return $(weekBar);
        },

        /**
         * 试图根据所传入的值创建一个有效的 moment 对象，如果无法创建或所创建的 moment 对象无效，
         * 则返回 undefined。
         */
        _getMomentDate: function(val, type) {
            var mdate;

            if (isDate(val) || isString(val) || isNumber(val)) {
                mdate =  moment(val);
            }
            else if (isFunction(val)) {
                mdate = moment(val());
            }

            return mdate && mdate.isValid() ? mdate : undefined;
        }
    };

    function Calendar(mdate) {
        mdate = moment(mdate).startOf('day');

        var prevMonthLastDate = moment(mdate).subtract(1, 'month').endOf('month').startOf('day'),    // 上一月的最后一天
            monthLastDate = moment(mdate).endOf('month').startOf('day');

        this._mdate = mdate,

        // 当前月历中所有 date 的数据
        this.mdates = [];
        // 当月长度
        this.monthLength = monthLastDate.date();
        // 上一月长度
        this.prevMonthLength = prevMonthLastDate.date();
        // 当月第一天为周几
        this.monthFirstDay = moment(mdate).startOf('month').day();
        // 今天的 date
        this.nowDay = moment().date();
        // 显示上一个月中的多少天
        this.prevMonthDateNumber = this.monthFirstDay;
        // 当前所显示月历的总星期数
        this.weekNumber = Math.ceil((this.prevMonthDateNumber + this.monthLength) / 7);
        // 当前所显示月历的总天数
        this.dateNumber = this.weekNumber * 7;
        // 显示下一个月中的多少天
        this.nextMonthDateNumber = this.dateNumber - this.prevMonthDateNumber - this.monthLength;


        var day = prevMonthLastDate.subtract(this.prevMonthDateNumber, 'day');

        for (var i = 0; i < this.dateNumber; i++) {
            this.mdates.push(moment(day.add(1, 'day')));
        }
    }

    var datePicker = window.anneBonny = new DatePicker();

    function isString(obj) {
        return typeof obj === 'string';
    }

    function isNumber(obj) {
        return typeof obj === 'number';
    }

    function isFunction(obj) {
        return typeof obj === 'function';
    }

    function isDate(obj) {
        return _toString.call(obj) === '[object Date]';
    }

    /**
     * Angular
     */
    if (angular) {
        angular.module('anneBonny', [])
            .constant('annebonnyConfig', {
                /**
                 * 是否禁用 AnneBonny
                 *
                 * 如果需要在某个设备平台中使用原生交互，而在另外一些原生交互体验不好的平台中使用 AnneBonny，
                 * 那么可以使用这个配置。
                 */
                disabled: false
            })
            .directive('annebonnyDatePicker', ['annebonnyConfig', function(annebonnyConfig) {
                return {
                    restrict: 'A',
                    require: 'ngModel',
                    link: function($scope, $el, $attrs, ngModel) {
                        if (annebonnyConfig.disabled) return;

                        var type = $el.attr('type') || 'date';

                        if (type === 'text') {
                            type = 'date';
                        }

                        $el.attr('readonly', true);

                        $el.on('mousedown', function(event) {
                            datePicker.setValue(ngModel.$modelValue).reset({
                                type: type,
                                events: {
                                    change: function(anneBonny) {
                                        ngModel.$setViewValue(anneBonny.getValue().format('YYYY-MM-DD'));
                                        ngModel.$commitViewValue();
                                        ngModel.$render();
                                    }
                                }
                            }).open();
                            event.preventDefault();
                        });
                    }
                };
            }]);
    }

})(window.angular, window.jQuery, window.moment);
