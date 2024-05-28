package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Reservations;
import com.ruoyi.system.service.IReservationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动预约Controller
 * 
 * @author rczmm
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/event/reservations")
public class ReservationsController extends BaseController
{
    @Autowired
    private IReservationsService reservationsService;

    /**
     * 查询活动预约列表
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reservations reservations)
    {
        startPage();
        List<Reservations> list = reservationsService.selectReservationsList(reservations);
        return getDataTable(list);
    }

    /**
     * 导出活动预约列表
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:export')")
    @Log(title = "活动预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reservations reservations)
    {
        List<Reservations> list = reservationsService.selectReservationsList(reservations);
        ExcelUtil<Reservations> util = new ExcelUtil<Reservations>(Reservations.class);
        util.exportExcel(response, list, "活动预约数据");
    }

    /**
     * 获取活动预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:query')")
    @GetMapping(value = "/{reservationID}")
    public AjaxResult getInfo(@PathVariable("reservationID") Long reservationID)
    {
        return success(reservationsService.selectReservationsByReservationID(reservationID));
    }

    /**
     * 新增活动预约
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:add')")
    @Log(title = "活动预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reservations reservations)
    {
        return toAjax(reservationsService.insertReservations(reservations));
    }

    /**
     * 修改活动预约
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:edit')")
    @Log(title = "活动预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reservations reservations)
    {
        return toAjax(reservationsService.updateReservations(reservations));
    }

    /**
     * 删除活动预约
     */
    @PreAuthorize("@ss.hasPermi('event:reservations:remove')")
    @Log(title = "活动预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reservationIDs}")
    public AjaxResult remove(@PathVariable Long[] reservationIDs)
    {
        return toAjax(reservationsService.deleteReservationsByReservationIDs(reservationIDs));
    }
}
