package com.dictation.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO{
	private String year;
	private String term;
	private int lecture_no;
	private String board_cd;
	private long no;
	private int seq_no;
	private String title;
	private String content;
	private int read_cnt;
	private String file_nm;
	private String save_file_nm;
	private String input_id;
	private Date input_date;
	private String update_id;
	private Date update_date;
	
	
	//board_cd���� dae_cd�� so_cd���� ��ġ�� ���� ����
	private String dae_b;
	private String so_b;
	//������ �����ߴ��� ���ߴ��� Ȯ���ϴ� ��
	private String file;
	
	/*����
	@Override
    public int compareTo(BoardVO s) {
        if (this.seq_no < s.getSeq_no()) {
            return -1;
        } else if (this.seq_no > s.getSeq_no()) {
            return 1;
        }
        return 0;
    }*/
	
}
