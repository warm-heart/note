package org.cloud.note.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cloud.note.dao.NoteDao;
import org.cloud.note.dao.UserDao;
import org.cloud.note.dto.ApiResponse;
import org.cloud.note.dto.NoteDTO;
import org.cloud.note.dto.ServiceResult;
import org.cloud.note.dto.UserDTO;
import org.cloud.note.entity.Note;
import org.cloud.note.entity.User;
import org.cloud.note.enums.ResultEnum;
import org.cloud.note.exception.UserException;
import org.cloud.note.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-02-13 14:29
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDao userDao;

    @Autowired
    NoteDao noteDao;

    @Override
    public ServiceResult<UserDTO> getAllUser(Integer page, Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = userDao.getTotal();
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error("无用户");
        }
        List<User> userList = userDao.getAllUser(page, size);

        UserDTO userDTO = new UserDTO(userList, total);
        return ServiceResult.success(userDTO);
    }

    @Override
    public ServiceResult<NoteDTO> getAllNoteByPage(Integer page, Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        Integer total = noteDao.getTotal();
        if (total == 0) {
            //throw new NoteException(ResultEnum.YOUR_NOTE_IS_EMPTY);
            return ServiceResult.error("无笔记");
        }
        List<Note> notes = noteDao.findAllNoteByPage(page, size);

        NoteDTO noteDTO = new NoteDTO(notes, total);
        return ServiceResult.success(noteDTO);
    }

    @Override
    @Transactional
    public ServiceResult<String> deBlockUser(Integer userId) {

        Integer res = userDao.deBlock(userId);
        if (1 == res) {
            return ServiceResult.success(ResultEnum.USER_ACCOUNT_DE_BLOCK_SUCCESS.getMessage());
        }
        throw new UserException(ResultEnum.USER_ACCOUNT_DE_BLOCK_FAIL);
    }

    @Override
    @Transactional
    public ServiceResult<String> lockUser(Integer userId) {
        Integer res = userDao.lockUser(userId);
        if (1 == res) {
            return ServiceResult.success(ResultEnum.USER_ACCOUNT_LOCK_SUCCESS.getMessage());
        }
        throw new UserException(ResultEnum.USER_ACCOUNT_LOCK_FAIL);
    }
}
